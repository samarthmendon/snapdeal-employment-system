package com.samarth.snapdeal.model;

import java.time.LocalDateTime;

public class Job {
    private String id;
    private String title;
    private String company;
    private String location;
    private String type;
    private String salary;
    private String description;
    private String postedBy;
    private String postedAt;

    public Job() {
    }

    public Job(String id, String title, String company, String location, String type, String salary,
               String description, String postedBy, String postedAt) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.location = location;
        this.type = type;
        this.salary = salary;
        this.description = description;
        this.postedBy = postedBy;
        this.postedAt = postedAt;
    }

    public static Job create(String id, String title, String company, String location, String type,
                            String salary, String description, String postedBy) {
        return new Job(id, title, company, location, type, salary, description, postedBy,
                LocalDateTime.now().toString());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(String postedAt) {
        this.postedAt = postedAt;
    }
}
