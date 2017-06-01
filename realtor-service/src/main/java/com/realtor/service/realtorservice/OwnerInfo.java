package com.realtor.service.realtorservice;

import lombok.Data;

/**
 * Created by omuliarevych on 5/30/17.
 */
@Data
public class OwnerInfo {
    protected String email;
    protected String name;
    protected String surname;

    public OwnerInfo() {
    }

    protected String phone;
    protected String info;

    public OwnerInfo(String email, String name, String surname, String phone, String info) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.info = info;
    }
}
