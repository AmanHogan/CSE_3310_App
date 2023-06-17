package com.example.cse3310defaultproject.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class User {

    private String userId;
    private String email;
    private String pwd;
    private String name;
    private String phoneNumber;
    private String address;
    private String cardNumber;
    private String expDateOnCard;
    private boolean isVendor;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpDateOnCard() {
        return expDateOnCard;
    }

    public void setExpDateOnCard(String expDateOnCard) {
        this.expDateOnCard = expDateOnCard;
    }




    public User(String name, String email, String pwd, String phoneNumber, String address) {
        this.userId = java.util.UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.isVendor = false;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public boolean isVendor() {
        return isVendor;
    }

    public boolean setVendor(boolean isVendor) {
        this.isVendor = isVendor;
        return this.isVendor;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public boolean verify(String newEmail, String newPwd) {
        return (this.email.equals(newEmail) && this.pwd.equals(newPwd));
    }
}