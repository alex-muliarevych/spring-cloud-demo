package com.realtor.service.realtorservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Class which contains information about home offer.
 */
@Entity
public class HomeOffer {

    @Id
    @GeneratedValue
    private Long id;

    private String ownerName;

    private String phoneNumber;

    private String location;

    private double price;

    private double sqft;

    public HomeOffer(String ownerName, String phoneNumber, String location, double price, double sqft) {
        this.ownerName = ownerName;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.price = price;
        this.sqft = sqft;
    }

    HomeOffer() {
    }

    public Long getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public double getPrice() {
        return price;
    }

    public double getSqft() {
        return sqft;
    }

    @Override
    public String toString() {
        return "HomeOffer{id=" + id.toString() +
                ", ownerName='" + ownerName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", location='" + location + '\'' +
                ", price=" + String.valueOf(price) +
                ", sqft=" + String.valueOf(sqft) +
                '}';
    }
}
