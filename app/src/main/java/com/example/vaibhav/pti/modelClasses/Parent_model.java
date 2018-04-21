package com.example.vaibhav.pti.modelClasses;


public class Parent_model {
    private String fname;
    private String address;
    private String email;
    private String phone;
    private String image;

    public Parent_model(String fname, String address, String email, String phone, String image) {
        this.fname = fname;
        this.address = address;
        this.email = email;
        this.image = image;
        this.phone = phone;
    }

    public String getImage() {
        return image;
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
