package com.example.elearningsystem;

public class course {
    private String title;
    private String des;
    private String coursename;
    private String mimagurl;

    public course() {
    }

    public course(String title, String des, String coursename, String mimagurl) {
        this.title = title;
        this.des = des;
        this.coursename = coursename;
        this.mimagurl = mimagurl;
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

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getMimagurl() {
        return mimagurl;
    }

    public void setMimagurl(String mimagurl) {
        this.mimagurl = mimagurl;
    }
}
