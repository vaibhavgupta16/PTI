package com.example.vaibhav.pti.modelClasses;


public class Progress_model {

    String cname, sub, smark, tmark;

    public Progress_model(String cname, String sub, String smark, String tmark) {
        this.cname = cname;
        this.sub = sub;
        this.smark = smark;
        this.tmark = tmark;
    }

    public String getCname() {
        return cname;
    }

    public String getSub() {
        return sub;
    }

    public String getSmark() {
        return smark;
    }

    public String getTmark() {
        return tmark;
    }


}
