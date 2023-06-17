package com.example.cse3310defaultproject.data.model;

import java.util.UUID;

public class Receipt {
    String ID;
    String serviceRequestID;
    String dateCompleted;
    float amountPaid;
    String paymentMethod;

    public Receipt(String serviceRequestID, String dateCompleted, float amountPaid, String paymentMethod) {
        this.ID = UUID.randomUUID().toString();
        this.serviceRequestID = serviceRequestID;
        this.dateCompleted = dateCompleted;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
    }

    public String getID() {
        return ID;
    }

    public float getAmountPaid() {
        return amountPaid;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getServiceRequestID() {
        return serviceRequestID;
    }

}
