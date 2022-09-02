package com.ironhack.BankingApp.models.accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.BankingApp.models.utilities.Money;
import com.ironhack.BankingApp.models.users.AccountHolder;
import com.ironhack.BankingApp.models.utilities.enums.Currency;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;

    @Embedded
    @NotNull
    private Money money;

    @NotNull
    private String secretKey;

    @ManyToOne
    @JoinColumn (name = "primaryOwner")
    @JsonIgnore
    @NotNull
    private AccountHolder primaryOwner;

    @ManyToOne
    @JoinColumn (name = "secondaryOwner")
    @JsonIgnore
    private AccountHolder secondaryOwner;

    @Embedded
    @Column(precision = 19, scale = 4)
    private static final Money penaltyFee = new Money(Currency.EURO, BigDecimal.valueOf(40.00));

    private LocalDate lastChecked = LocalDate.now();

    //constructor
    public Account(Money money, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
    }

    public Account() {

    }


    public void applyPenaltyFee() {
        this.getMoney().setAmount(this.getMoney().getAmount().subtract(getPenaltyFee().getAmount()));
    }

    //getters
    public Long getId() {
        return id;
    }

    public Money getMoney() {
        return money;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public Money getPenaltyFee() {
        return penaltyFee;
    }

    public LocalDate getLastChecked() {
        return lastChecked;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public void setLastChecked(LocalDate lastChecked) {
        this.lastChecked = lastChecked;
    }
}
