package com.ironhack.BankingApp.models.utilities;

import com.sun.istack.NotNull;

import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;

@Embeddable
public class Address {

    @NotNull
    private String address;
    @NotNull
    @Digits(integer = 5, fraction = 0)
    private Integer postalCode;
    @NotNull
    private String city;
    @NotNull
    private String country;

}
