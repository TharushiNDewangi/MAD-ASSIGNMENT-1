package com.example.mad;

public class ProfileHelper {
        private String name;
        private String username;
        private String course;
        private String phone;
        private String password;

    public ProfileHelper(String name, String username, String course, String phone, String password) {
        this.name = name;
        this.username = username;
        this.course = course;
        this.phone = phone;
        this.password = password;
    }
    public ProfileHelper(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
