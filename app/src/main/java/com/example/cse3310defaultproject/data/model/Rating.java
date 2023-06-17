package com.example.cse3310defaultproject.data.model;

import java.util.UUID;

public class Rating {
    private String ID;
    private String text;
    private double rating;
    private String userID;
    private String vendorID;

    public Rating(String text, double rating, String userID, String vendorID) {
        this.ID = UUID.randomUUID().toString();
        this.text = text;

        if (rating < 0.0)
            rating = 0.0;
        if (rating > 5.0)
            rating = 5.0;
        this.rating = rating;
        this.userID = userID;
        this.vendorID = vendorID;
    }

    public String getID() {
        return ID;
    }

    public String getVendorID() {
        return vendorID;
    }

    public double getRating() {
        return rating;
    }

    public String getText() {
        return text;
    }

    public String getUserID() {
        return userID;
    }
}
