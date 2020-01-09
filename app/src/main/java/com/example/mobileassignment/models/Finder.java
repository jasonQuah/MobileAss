package com.example.mobileassignment.models;

public class Finder {
    private String user_id;
    private String age;

    public Finder(String user_id, String age) {
        this.user_id = user_id;
        this.age = age;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
