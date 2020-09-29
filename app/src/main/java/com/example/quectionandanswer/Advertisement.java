package com.example.quectionandanswer;

public class Advertisement {
    private String title;
    private String name;
    private String mobile;
    private String email;
    private String description;
    private String imageUrl;


    public Advertisement(String title, String name, String mobile, String email, String description, String imageUrl) {
        this.title=title;
        this.name=name;
        this.mobile = mobile;
        this.email=email;
        this.description = description;
        this.imageUrl=imageUrl;
    }
    public Advertisement() {

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
