package com.ironhack.BankingApp.models.users;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Role(String role, User user) {
        this.role = role;
        this.user = user;
    }

    public Role() {

    }

    //getters
    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public User getUser() {
        return user;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
