package com.ironhack.BankingApp.models.users;

import javax.persistence.Entity;

@Entity
public class ThirdParty extends User {

    private String secretKey;

    //constructors
    public ThirdParty(String username, String password, String secretKey) {
        super(username, password);
        this.secretKey = secretKey;
    }

    public ThirdParty() {

    }

    //getters
    public String getSecretKey() {
        return secretKey;
    }

    //setters
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
