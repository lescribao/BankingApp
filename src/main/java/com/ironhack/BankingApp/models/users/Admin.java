package com.ironhack.BankingApp.models.users;

import javax.persistence.Entity;

@Entity
public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password);
    }

    public Admin(){

    };


}
