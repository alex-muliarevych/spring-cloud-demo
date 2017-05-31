package com.realtor.service.realtorservice;

import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by omuliarevych on 5/30/17.
 */
@Data
public class AppartmentInfo {
    protected String ownerMail;
    protected String city;
    protected Double price;
    protected Double sqft;
    protected Integer flatCount;
    protected String address;
}
