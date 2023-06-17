package com.example.cse3310defaultproject.data.model;

import java.util.UUID;

public class ServiceRequest {

    String ID;
    String name;
    String date;
    String desc;
    boolean completed;
    String customerID;
    String vendorID;
    double price;
    String receiptID;
    ServiceCategory serviceCategory;

    public ServiceRequest(String customerID, String desc, String date, double price, ServiceCategory serviceCategory) {
        this.ID = UUID.randomUUID().toString();
        this.date = date;
        this.desc = desc;
        this.completed = false;
        this.customerID = customerID;
        this.vendorID = "";
        this.price = price;
        this.receiptID = "";
        this.serviceCategory = serviceCategory;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCustomerID() {
        return customerID;
    }

    public ServiceCategory getServiceCategory() {
        return serviceCategory;
    }

    public String getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }

    public String getReceiptID() {
        return receiptID;
    }

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }
}
