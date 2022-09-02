package com.ironhack.BankingApp.models.users;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {

    @NotNull
    @Id
    private String username;
    @NotNull
    private String password;

    //constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    //getter
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    //setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
