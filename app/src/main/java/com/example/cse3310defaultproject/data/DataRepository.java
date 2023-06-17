package com.example.cse3310defaultproject.data;

import android.app.Service;

import com.example.cse3310defaultproject.data.model.Rating;
import com.example.cse3310defaultproject.data.model.User;
import com.example.cse3310defaultproject.data.model.Receipt;
import com.example.cse3310defaultproject.data.model.ServiceCategory;
import com.example.cse3310defaultproject.data.model.ServiceRequest;
import com.example.cse3310defaultproject.data.model.Vendor;

import java.io.IOException;
import java.util.ArrayList;

public class DataRepository {

    private static DataRepository INSTANCE = null;

    User currentUser;
    ArrayList<User> users;
    ArrayList<Vendor> vendors;
    ArrayList<ServiceRequest> serviceRequests;
    ArrayList<Receipt> receipts;
    ArrayList<Rating> ratings;

    private DataRepository() {}

    public static DataRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataRepository();
            INSTANCE.currentUser = null;
            INSTANCE.vendors = new ArrayList<>();
            INSTANCE.serviceRequests = new ArrayList<>();
            INSTANCE.receipts = new ArrayList<>();
            INSTANCE.users = new ArrayList<>();
            INSTANCE.ratings = new ArrayList<>();
            INSTANCE.populateDummyData();
        }
        return(INSTANCE);
    }


    // This puts some data in the repository that we can play around with.
    // This is a temporary replacement for a real persistent database
    private void populateDummyData() {
        this.vendors.add(new Vendor("Plumbing Inc.", "1234567890", "plumbing@inc.com", "123 Victoria Ave", ServiceCategory.Plumbing));
        this.vendors.add(new Vendor("Tutoring Inc.", "1234567890", "tutoring@inc.com", "123 Victoria Ave",  ServiceCategory.Tutoring));
        this.vendors.add(new Vendor("Electrical Inc.", "1234567890", "electrical@inc.com", "123 Victoria Ave", ServiceCategory.Electrical));
        this.vendors.add(new Vendor("Appliances Inc.", "1234567890", "applicances@inc.com", "123 Victoria Ave", ServiceCategory.Appliances));
        this.vendors.add(new Vendor("Home Cleaning Inc.", "1234567890", "home_cleaning@inc.com", "123 Victoria Ave", ServiceCategory.HomeCleaning));
        this.vendors.add(new Vendor("Packaging & Moving Inc.", "1234567890", "packaging_moving@inc.com", "123 Victoria Ave", ServiceCategory.Moving));

        this.vendors.get(0).setRating((float) 4.5);
        this.vendors.get(1).setRating((float) 4.8);
        this.vendors.get(2).setRating((float) 3.2);
        this.vendors.get(3).setRating((float) 5.0);
        this.vendors.get(4).setRating((float) 3.8);
        this.vendors.get(5).setRating((float) 4.3);


        this.users.add(new User("Luke", "luke@testing.com", "password", "1234567890", "123 Victoria Ave"));
        this.users.add(new User("Aman", "aman@testing.com", "password", "1234567890", "123 Victoria Ave"));
        this.users.add(new User("Ryan", "ryan@testing.com", "password", "1234567890", "123 Victoria Ave"));
        this.users.add(new User("Andy", "andy@testing.com", "password", "1234567890", "123 Victoria Ave"));

        this.serviceRequests.add(new ServiceRequest(
                this.users.get(0).getUserId(),
                "Service request description goes here",
                "28/04/2022",
                149.99,
                ServiceCategory.Appliances
        ));
        this.serviceRequests.get(0).setVendorID(this.getVendors().get(0).getID());
        this.serviceRequests.add(new ServiceRequest(
                this.users.get(1).getUserId(),
                "Service request description goes here",
                "30/04/2022",
                55.0,
                ServiceCategory.Tutoring
        ));
        this.serviceRequests.get(1).setVendorID(this.getVendors().get(3).getID());
        this.serviceRequests.add(new ServiceRequest(
                this.users.get(2).getUserId(),
                "Service request description goes here",
                "14/06/2022",
                200.0,
                ServiceCategory.Moving
        ));
        this.serviceRequests.get(2).setVendorID(this.getVendors().get(2).getID());
        this.serviceRequests.add(new ServiceRequest(
                this.users.get(3).getUserId(),
                "Service request description goes here",
                "29/4/2022",
                45.50,
                ServiceCategory.Plumbing
        ));
        this.serviceRequests.get(3).setVendorID(this.getVendors().get(0).getID());

        this.ratings.add(new Rating(
                "Rating content",
                4.5,
                this.users.get(0).getUserId(),
                this.vendors.get(2).getID()
        ));
        this.ratings.add(new Rating(
                "Rating content goes here",
                3.0,
                this.users.get(2).getUserId(),
                this.vendors.get(0).getID()
        ));
    }

    public Result<User> login(String email, String password) {
        for (User user : this.users) {
            if (user.verify(email, password)) {
                setCurrentUser(user);
                return new Result.Success<>(user);
            }
        }

        return new Result.Error(new IOException("Error logging in"));
    }


    public boolean isLoggedIn() {
        return this.currentUser != null;
    }

    public void logout() {
        this.currentUser = null;
    }

    // Getters and Setters ---------------------------------------

    public ArrayList<Receipt> getReceipts() {
        return receipts;
    }

    // Adds a new receipt, returns the receipt ID
    public String addReceipt(String serviceRequestID, String dateCompleted, float amountPaid, String paymentMethod) {
        Receipt new_receipt = new Receipt(serviceRequestID, dateCompleted, amountPaid, paymentMethod);
        this.receipts.add(new_receipt);
        return new_receipt.getID();
    }

    public Receipt getReceiptByID(String ID) {
        for (Receipt r : this.receipts) {
            if (r.getID().equals(ID)) {
                return r;
            }
        }
        return null;
    }

    public ArrayList<ServiceRequest> getServiceRequests() {
        return serviceRequests;
    }

    // Adds a new service request, returns the service request ID
    public String addServiceRequest(String customerID, String desc, String date, double price, ServiceCategory serviceCategory) {
        ServiceRequest new_sr = new ServiceRequest(customerID, desc, date, price, serviceCategory);
        this.serviceRequests.add(new_sr);
        return new_sr.getID();
    }

    public ServiceRequest getServiceRequestByID(String ID) {
        for (ServiceRequest sr : this.serviceRequests) {
            if (sr.getID().equals(ID)) {
                return sr;
            }
        }
        return null;
    }

    public ArrayList<Vendor> getVendors() {
        return vendors;
    }

    // Add a new vendor, return the vendor ID
    public String addVendor(String name, String phoneNumber, String email, String address, ServiceCategory serviceCategory) {
        Vendor new_vendor = new Vendor(name, phoneNumber, email, address, serviceCategory);
        this.vendors.add(new_vendor);
        return new_vendor.getID();
    }

    public Vendor getVendorByID(String ID) {
        for (Vendor v : this.vendors) {
            if (v.getID().equals(ID)) {
                return v;
            }
        }
        return null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String addUser(String name, String email, String pwd, String phoneNumber, String address) {
        User new_user = new User(name, email, pwd, phoneNumber, address);
        this.users.add(new_user);
        return new_user.getUserId();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getUserByID(String ID) {
        for (User user : this.users) {
            if (user.getUserId().equals(ID)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public Rating getRatingByID(String ID) {
        for (Rating rating : this.ratings) {
            if (rating.getID().equals(ID)) {
                return rating;
            }
        }
        return null;
    }

    public String addRating(String text, double rating, String userID, String vendorID) {
        Rating newRating = new Rating(text, rating, userID, vendorID);
        this.ratings.add(newRating);
        return newRating.getID();
    }
}
