const API_BASE = '/api';

function setStatus(elementId, message, type) {
    const el = document.getElementById(elementId);
    if (!el) return;
    el.textContent = message;
    el.classList.remove('success', 'error');
    el.classList.add(type);
    el.classList.add('show');
}

function clearStatus(elementId) {
    const el = document.getElementById(elementId);
    if (!el) return;
    el.textContent = '';
    el.classList.remove('show', 'success', 'error');
}

function saveUser(user) {
    localStorage.setItem('snapdealUser', JSON.stringify(user));
}

function getCurrentUser() {
    const raw = localStorage.getItem('snapdealUser');
    return raw ? JSON.parse(raw) : null;
}

function logoutUser() {
    localStorage.removeItem('snapdealUser');
    window.location.href = 'index.html';
}

function handleAuthPage() {
    const signupTab = document.getElementById('signupTab');
    const loginTab = document.getElementById('loginTab');
    const signupFormBox = document.getElementById('signupFormBox');
    const loginFormBox = document.getElementById('loginFormBox');

    if (!signupTab || !loginTab) return;

    const user = getCurrentUser();
    if (user) {
        window.location.href = 'dashboard.html';
        return;
    }

    signupTab.addEventListener('click', () => {
        signupTab.classList.add('active');
        loginTab.classList.remove('active');
        signupFormBox.classList.add('active');
        loginFormBox.classList.remove('active');
        clearStatus('signupStatus');
        clearStatus('loginStatus');
    });

    loginTab.addEventListener('click', () => {
        loginTab.classList.add('active');
        signupTab.classList.remove('active');
        loginFormBox.classList.add('active');
        signupFormBox.classList.remove('active');
        clearStatus('loginStatus');
        clearStatus('signupStatus');
    });

    const signupForm = document.getElementById('signupForm');
    const loginForm = document.getElementById('loginForm');

    if (signupForm) {
        signupForm.addEventListener('submit', async (event) => {
            event.preventDefault();
            const payload = {
                name: document.getElementById('signupName').value,
                email: document.getElementById('signupEmail').value,
                password: document.getElementById('signupPassword').value,
                role: document.getElementById('signupRole').value
            };

            try {
                const response = await fetch(`${API_BASE}/auth/signup`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(payload)
                });

                const data = await response.json();
                if (!response.ok) {
                    setStatus('signupStatus', data.message || 'Signup failed.', 'error');
                    return;
                }

                setStatus('signupStatus', data.message || 'Account created successfully!', 'success');
                saveUser(data.user);
                setTimeout(() => window.location.href = 'dashboard.html', 700);
            } catch (error) {
                setStatus('signupStatus', 'Something went wrong while creating your account.', 'error');
            }
        });
    }

    if (loginForm) {
        loginForm.addEventListener('submit', async (event) => {
            event.preventDefault();
            const payload = {
                email: document.getElementById('loginEmail').value,
                password: document.getElementById('loginPassword').value
            };

            try {
                const response = await fetch(`${API_BASE}/auth/login`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(payload)
                });

                const data = await response.json();
                if (!response.ok) {
                    setStatus('loginStatus', data.message || 'Login failed.', 'error');
                    return;
                }

                setStatus('loginStatus', data.message || 'Logged in successfully!', 'success');
                saveUser(data.user);
                setTimeout(() => window.location.href = 'dashboard.html', 700);
            } catch (error) {
                setStatus('loginStatus', 'Could not login right now. Try again.', 'error');
            }
        });
    }
}

function renderJobs(jobs) {
    const jobsList = document.getElementById('jobsList');
    const jobsCount = document.getElementById('jobsCount');
    if (!jobsList) return;

    jobsList.innerHTML = '';
    if (jobsCount) jobsCount.textContent = jobs.length;

    jobs.forEach(job => {
        const card = document.createElement('div');
        card.className = 'job-card';
        card.innerHTML = `
            <div class="job-card-header">
                <h3>${job.title}</h3>
                <span class="job-tag">${job.type}</span>
            </div>
            <div class="job-meta">
                <span>${job.company}</span>
                <span>•</span>
                <span>${job.location}</span>
                <span>•</span>
                <span>${job.salary}</span>
            </div>
            <p>${job.description}</p>
            <div class="job-actions">
                <strong>Posted by: ${job.postedBy}</strong>
                <button class="ghost-btn">Apply now</button>
            </div>
        `;
        jobsList.appendChild(card);
    });
}

async function loadJobs() {
    try {
        const response = await fetch(`${API_BASE}/jobs`);
        const jobs = await response.json();
        renderJobs(jobs);
    } catch (error) {
        const jobsList = document.getElementById('jobsList');
        if (jobsList) {
            jobsList.innerHTML = '<p>Unable to load jobs right now.</p>';
        }
    }
}

function handleDashboard() {
    const user = getCurrentUser();
    if (!user) {
        window.location.href = 'index.html';
        return;
    }

    const userBadge = document.getElementById('userBadge');
    const profileName = document.getElementById('profileName');
    const profileRole = document.getElementById('profileRole');
    if (userBadge) userBadge.textContent = user.name + ' • ' + user.role;
    if (profileName) profileName.textContent = 'Name: ' + user.name;
    if (profileRole) profileRole.textContent = 'Role: ' + user.role;

    const logoutBtn = document.getElementById('logoutBtn');
    if (logoutBtn) {
        logoutBtn.addEventListener('click', logoutUser);
    }

    const addJobToggle = document.getElementById('addJobToggle');
    const jobFormWrap = document.getElementById('jobFormWrap');
    if (addJobToggle && jobFormWrap) {
        addJobToggle.addEventListener('click', () => {
            jobFormWrap.classList.toggle('hidden');
        });
    }

    const jobForm = document.getElementById('jobForm');
    if (jobForm) {
        jobForm.addEventListener('submit', async (event) => {
            event.preventDefault();
            const payload = {
                title: document.getElementById('jobTitle').value,
                company: document.getElementById('jobCompany').value,
                location: document.getElementById('jobLocation').value,
                type: document.getElementById('jobType').value,
                salary: document.getElementById('jobSalary').value,
                description: document.getElementById('jobDescription').value,
                postedBy: user.name
            };

            try {
                const response = await fetch(`${API_BASE}/jobs`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(payload)
                });

                const data = await response.json();
                if (!response.ok) {
                    setStatus('jobStatus', data.message || 'Unable to post the job.', 'error');
                    return;
                }

                setStatus('jobStatus', 'Job posted successfully!', 'success');
                jobForm.reset();
                loadJobs();
            } catch (error) {
                setStatus('jobStatus', 'Could not post the job at the moment.', 'error');
            }
        });
    }

    loadJobs();
}

document.addEventListener('DOMContentLoaded', () => {
    const path = window.location.pathname.split('/').pop();
    if (path === 'dashboard.html') {
        handleDashboard();
    } else {
        handleAuthPage();
    }
});
