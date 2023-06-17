package com.example.cse3310defaultproject.data.model;

import java.util.UUID;

public class Vendor {
    private String ID;
    private String userID;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private String pwd;
    private String cardNumber;
    private String expDate;
    private ServiceCategory serviceCategory;
    private float rating;




    public Vendor(String name, String phoneNumber, String email, String address, ServiceCategory serviceCategory) {
        this.ID = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.pwd = pwd;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.serviceCategory = serviceCategory;
        this.userID = null;
        this.rating = (float) 0.0;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public String getEmail() {
        return email;
    }

    public float getRating() {
        return rating;
    }

    public ServiceCategory getServiceCategory() {
        return serviceCategory;
    }

    public String getAddress() {
        return address;
    }

    public String getPwd() {
        return pwd;
    }

    public void attachToUser(String userID) {
        this.userID = userID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
