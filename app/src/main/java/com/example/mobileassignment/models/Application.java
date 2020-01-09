package com.example.mobileassignment.models;

public class Application {
    private String job_id;
    private String user_id;
    private String status;

    public Application(){

    }

    public Application(String job_id, String user_id, String status) {
        this.job_id = job_id;
        this.user_id = user_id;
        this.status = status;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
