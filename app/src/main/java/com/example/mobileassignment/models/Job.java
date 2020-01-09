package com.example.mobileassignment.models;

public class Job {
    private String job_id;
    private String job_Position;
    private String job_description;
    private String salary;
    private String job_requirement;
    private String category_name;
    private String user_id;

    public Job(){
    }

    public Job(String job_id, String job_Position, String job_description, String salary, String job_requirement, String category_name, String user_id){
        this.job_id = job_id;
        this.job_Position = job_Position;
        this.job_description = job_description;
        this.salary = salary;
        this.job_requirement = job_requirement;
        this.category_name = category_name;
        this.user_id = user_id;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getJob_Position() {
        return job_Position;
    }

    public void setJob_Position(String job_Position) {
        this.job_Position = job_Position;
    }

    public String getJob_description() {
        return job_description;
    }

    public String getcategory_name() {
        return category_name;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getJob_requirement() {
        return job_requirement;
    }

    public void setJob_requirement(String job_requirement) {
        this.job_requirement = job_requirement;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setCategory_name(String category_name){
        this.category_name = category_name;
    }
}
