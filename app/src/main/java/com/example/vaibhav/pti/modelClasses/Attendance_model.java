package com.example.vaibhav.pti.modelClasses;


public class Attendance_model {
    private String att;
    private String cl;
    private String name;
    private String result;

    public Attendance_model(String result) {
        this.result = result;
    }

    public Attendance_model(String att, String name, String cl) {
        this.att = att;
        this.name = name;
        this.cl = cl;
    }

    public String getResult() {
        return result;
    }

    public String getAtt() {
        return att;
    }

    public String getName() {
        return name;
    }

    public String getCl() {
        return cl;
    }
}
