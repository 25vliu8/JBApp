package com.example.jbapp;

import com.google.firebase.firestore.PropertyName;

import java.io.Serializable;

public class Job implements Serializable {
    private final String job_title;
    private String location;
    private final String company;
    private final String salary;

    public Job() {
        this.job_title = "default";
        this.company = "default";
        this.salary = "default";
        this.location = "";
    }

    public Job(String job_title, String company, String salary, String location) {
        this.job_title = job_title;
        this.company = company;
        this.salary = salary;
        this.location = location;


    }

    @PropertyName("job_title")
    public String getJobTitle() {
        return job_title;
    }

    @PropertyName("company")
    public String getCompany() {
        return company;
    }

    @PropertyName("salary")
    public String getSalary() {
        return salary;
    }

    @PropertyName("location")
    public String getLocation() {
        return location;
    }
}



