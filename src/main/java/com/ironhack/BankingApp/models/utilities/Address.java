package com.ironhack.BankingApp.models.utilities;

import com.sun.istack.NotNull;

import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;

@Embeddable
public class Address {

    @NotNull
    private String address;
    @NotNull
    @Digits(integer = 6, fraction = 0)
    private Integer postalCode;
    @NotNull
    private String city;
    @NotNull
    private String country;

    public Address(String address, Integer postalCode, String city, String country) {
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public Address () {

    }

    //getters
    public String getAddress() {
        return address;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    //setters
    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
