package com.example.vaibhav.pti.ModelClasses;

/**
 * Created by Vaibhav on 13-Apr-18.
 */

public class Attendance_model {
    private String att;
    private String cl;
    private String name;

    public Attendance_model(String att, String name, String cl) {
        this.att = att;
        this.name = name;
        this.cl = cl;
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
