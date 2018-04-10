package com.example.vaibhav.pti.ModelClasses;


public class Parent_model {
    private String fname;
    private String address;
    private String email;
    private String phone;

    public Parent_model(String fname, String address, String email, String phone) {
        this.fname = fname;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public Parent_model() {

    }

    public String getFname() {
        return fname;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

}
