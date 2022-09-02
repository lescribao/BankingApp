package com.ironhack.BankingApp.dtos;

import com.ironhack.BankingApp.models.users.AccountHolder;
import com.ironhack.BankingApp.models.utilities.Money;
import com.ironhack.BankingApp.models.utilities.enums.Currency;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountDTO {


    @NotNull
    private BigDecimal balance;

    private Currency currency;

    @NotNull
    private String secretKey;

    private AccountHolder secondaryOwner;

    private BigDecimal minimumBalance;

    private LocalDate creationDate;

    private BigDecimal interestRate;

    private BigDecimal creditLimit;

    //constructor
    public AccountDTO(BigDecimal balance, Currency currency, String secretKey, AccountHolder secondaryOwner, BigDecimal minimumBalance, LocalDate creationDate, BigDecimal interestRate, BigDecimal creditLimit) {
        this.balance = balance;
        this.currency = currency;
        this.secretKey = secretKey;
        this.secondaryOwner = secondaryOwner;
        this.minimumBalance = minimumBalance;
        this.creationDate = creationDate;
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
    }


    //getters
    public BigDecimal getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    //setters
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }
}
