package com.example.vaibhav.pti.modelClasses;


public class Diary_model {
    private String class_name;
    private String notice;
    private String date;

    public Diary_model(String class_name, String notice, String date) {
        this.class_name = class_name;
        this.notice = notice;
        this.date = date;
    }

    public String getClass_name() {
        return class_name;
    }

    public String getNotice() {
        return notice;
    }

    public String getDate() {
        return date;
    }
}
