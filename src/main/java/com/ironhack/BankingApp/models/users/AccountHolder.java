package com.ironhack.BankingApp.models.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.ironhack.BankingApp.models.accounts.Account;
import com.ironhack.BankingApp.models.utilities.Address;
import com.ironhack.BankingApp.models.utilities.Transference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AccountHolder extends User {

    @NotNull
    @JsonFormat (pattern = "DD-MM-YYYY")
    private LocalDate dateOfBirth;
    @Embedded
    @NotNull
    private Address primaryAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "SECONDARY_NAME")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "SECONDARY_POSTAL_CODE")),
            @AttributeOverride(name = "city", column = @Column(name = "SECONDARY_CITY")),
            @AttributeOverride(name = "country", column = @Column(name = "SECONDARY_COUNTRY"))
    })
    private Address secondaryAddress;

    @OneToMany (mappedBy = "primaryOwner")
    private List<Account> primaryAccountList = new ArrayList<>();

    @OneToMany (mappedBy = "secondaryOwner")
    private List<Account> sndAccountList = new ArrayList<>();


    @OneToMany (mappedBy = "moneySender")
    private List<Transference> sentTransferenceList = new ArrayList<>();

    @OneToMany (mappedBy = "moneyReceiver")
    private List<Transference> receiveTransferenceList = new ArrayList<>();


    //constructor
    public AccountHolder(String username, String password, LocalDate dateOfBirth, Address primaryAddress, Address secondaryAddress) {
        super(username, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.secondaryAddress = secondaryAddress;
    }

    public AccountHolder() {

    }

    //getters
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public Address getSecondaryAddress() {
        return secondaryAddress;
    }

    public List<Account> getPrimaryAccountList() {
        return primaryAccountList;
    }

    public List<Account> getSndAccountList() {
        return sndAccountList;
    }

    public List<Transference> getSentTransferenceList() {
        return sentTransferenceList;
    }

    public List<Transference> getReceiveTransferenceList() {
        return receiveTransferenceList;
    }

    //setters
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public void setSecondaryAddress(Address secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }

    public void setPrimaryAccountList(List<Account> primaryAccountList) {
        this.primaryAccountList = primaryAccountList;
    }

    public void setSndAccountList(List<Account> sndAccountList) {
        this.sndAccountList = sndAccountList;
    }

    public void setSentTransferenceList(Transference transference) {
        this.sentTransferenceList.add(transference);
    }

    public void setReceiveTransferenceList(List<Transference> receiveTransferenceList) {
        this.receiveTransferenceList = receiveTransferenceList;
    }
}
