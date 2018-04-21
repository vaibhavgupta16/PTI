package com.example.vaibhav.pti.modelClasses;


public class Teacher_model {
    private String tid;
    private String tname;
    private String tsub;

    public Teacher_model(String tid, String tname, String tsub) {
        this.tid = tid;
        this.tname = tname;
        this.tsub = tsub;
    }

    public String getTid() {
        return tid;
    }

    public String getTname() {
        return tname;
    }

    public String getTsub() {
        return tsub;
    }


}
