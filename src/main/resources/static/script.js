<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Snapdeal Dashboard</title>
    <link rel="stylesheet" href="styles.css" />
</head>
<body>
    <header class="topbar">
        <div class="brand">
            <div class="logo">S</div>
            <div class="brand-text">Snapdeal</div>
        </div>
        <nav class="nav-links">
            <a href="#">Dashboard</a>
            <a href="#">Jobs</a>
            <a href="#">Applications</a>
            <a href="#">Profile</a>
        </nav>
    </header>

    <main class="dashboard-shell">
        <div class="dashboard-top">
            <h2>Welcome back</h2>
            <div class="user-chip" id="userBadge">Loading...</div>
        </div>

        <div class="dashboard-grid">
            <section class="panel main-panel">
                <div class="quick-stats">
                    <div class="metric">
                        <b id="jobsCount">0</b>
                        Active jobs
                    </div>
                    <div class="metric">
                        <b>46</b>
                        New applicants
                    </div>
                    <div class="metric">
                        <b>18</b>
                        Interviews
                    </div>
                </div>

                <div>
                    <h3>Latest job openings</h3>
                    <div id="jobsList" class="job-list"></div>
                </div>
            </section>

            <aside class="panel side-panel">
                <div class="profile-card">
                    <h3>Profile</h3>
                    <p id="profileName">Loading...</p>
                    <p id="profileRole">Loading...</p>
                </div>

                <div>
                    <h3>Quick actions</h3>
                    <button id="logoutBtn" class="secondary-btn" style="width: 100%; margin-bottom: 12px;">Logout</button>
                    <button id="addJobToggle" class="ghost-btn" style="width: 100%;">Post a new job</button>
                </div>

                <div id="jobFormWrap" class="post-form hidden">
                    <h3>Post a new job</h3>
                    <form id="jobForm">
                        <div>
                            <label for="jobTitle">Title</label>
                            <input id="jobTitle" type="text" placeholder="e.g. Web Designer" required />
                        </div>
                        <div>
                            <label for="jobCompany">Company</label>
                            <input id="jobCompany" type="text" placeholder="Company name" required />
                        </div>
                        <div>
                            <label for="jobLocation">Location</label>
                            <input id="jobLocation" type="text" placeholder="City, State" required />
                        </div>
                        <div>
                            <label for="jobType">Type</label>
                            <select id="jobType">
                                <option value="Full Time">Full Time</option>
                                <option value="Internship">Internship</option>
                                <option value="Contract">Contract</option>
                            </select>
                        </div>
                        <div>
                            <label for="jobSalary">Salary</label>
                            <input id="jobSalary" type="text" placeholder="₹5 LPA or ₹20k/month" />
                        </div>
                        <div>
                            <label for="jobDescription">Description</label>
                            <textarea id="jobDescription" rows="4" placeholder="Describe the role and expectations" style="width: 100%; padding: 12px; border-radius: 12px; border: 2px solid #d8d2ff;"></textarea>
                        </div>
                        <button type="submit" class="primary-btn">Publish job</button>
                    </form>
                    <div id="jobStatus" class="status"></div>
                </div>
            </aside>
        </div>
    </main>

    <script src="script.js"></script>
</body>
</html>
