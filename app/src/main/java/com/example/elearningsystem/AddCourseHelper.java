package com.example.elearningsystem;

public class AddCourseHelper {

    String coursename;
    String title;
    String des;
    String mimageurl;

    public AddCourseHelper(String coursename, String title, String des, String mimageurl) {
        this.coursename = coursename;
        this.title = title;
        this.des = des;
        this.mimageurl = mimageurl;
    }

    public AddCourseHelper(String coursename, String title, String des) {
        this.coursename = coursename;
        this.title = title;
        this.des = des;
    }

    public AddCourseHelper(String title, String des) {
        this.title = title;
        this.des = des;
    }

    public AddCourseHelper() {
    }

    public String getMimageurl() {
        return mimageurl;
    }

    public void setMimageurl(String mimageurl) {
        this.mimageurl = mimageurl;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
