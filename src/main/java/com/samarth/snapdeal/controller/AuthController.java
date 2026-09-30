package com.samarth.snapdeal.service;

import com.samarth.snapdeal.model.Job;
import com.samarth.snapdeal.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SnapdealDataStore {
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Job> jobs = new ConcurrentHashMap<>();

    public SnapdealDataStore() {
        seedUsers();
        seedJobs();
    }

    private void seedUsers() {
        User admin = User.create(UUID.randomUUID().toString(), "Aarav Sharma", "admin@snapdeal.com", "admin123", "employer");
        users.put(admin.getEmail().toLowerCase(), admin);

        User student = User.create(UUID.randomUUID().toString(), "Meera Nair", "student@snapdeal.com", "student123", "job-seeker");
        users.put(student.getEmail().toLowerCase(), student);
    }

    private void seedJobs() {
        Job job1 = Job.create(UUID.randomUUID().toString(), "Frontend Developer Intern", "TechNova Labs", "Bangalore, India",
                "Internship", "₹25k/month", "Need a student who is good with HTML, CSS, JavaScript and can work on responsive pages.", "Aarav Sharma");
        jobs.put(job1.getId(), job1);

        Job job2 = Job.create(UUID.randomUUID().toString(), "Java Backend Engineer", "CampusCore", "Hyderabad, India",
                "Full Time", "₹8 LPA", "Build and support backend services with Spring Boot, APIs, and database integration.", "Aarav Sharma");
        jobs.put(job2.getId(), job2);

        Job job3 = Job.create(UUID.randomUUID().toString(), "Data Analyst", "MotiveFlow", "Pune, India",
                "Contract", "₹6 LPA", "Work on dashboards, Excel reporting, SQL, and simple data cleaning tasks.", "Aarav Sharma");
        jobs.put(job3.getId(), job3);
    }

    public User signup(String name, String email, String password, String role) {
        String safeEmail = email.trim().toLowerCase();
        if (users.containsKey(safeEmail)) {
            throw new IllegalArgumentException("An account already exists for this email.");
        }

        User user = User.create(UUID.randomUUID().toString(), name.trim(), safeEmail, password, role);
        users.put(safeEmail, user);
        return user;
    }

    public User login(String email, String password) {
        String safeEmail = email.trim().toLowerCase();
        User user = users.get(safeEmail);
        if (user == null || !Objects.equals(user.getPassword(), password)) {
            throw new IllegalArgumentException("Invalid email or password.");
        }
        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public List<Job> getAllJobs() {
        return new ArrayList<>(jobs.values());
    }

    public Job createJob(String title, String company, String location, String type, String salary,
                         String description, String postedBy) {
        Job job = Job.create(UUID.randomUUID().toString(), title.trim(), company.trim(), location.trim(),
                type.trim(), salary.trim(), description.trim(), postedBy.trim());
        jobs.put(job.getId(), job);
        return job;
    }
}
