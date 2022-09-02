package com.ironhack.BankingApp.models.accounts;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ironhack.BankingApp.models.utilities.Money;
import com.ironhack.BankingApp.models.accounts.enums.Status;
import com.ironhack.BankingApp.models.users.AccountHolder;
import com.ironhack.BankingApp.models.utilities.enums.Currency;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class CheckingAccount extends Account{

    @Embedded
    private final static Money minimumBalance = new Money(Currency.EURO, BigDecimal.valueOf(250.00));

    @Embedded
    private final static Money monthlyMaintFee = new Money(Currency.EURO, BigDecimal.valueOf(12.00));

    @NotNull
    @JsonFormat(pattern = "DD-MM-YYYY")
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;


    public CheckingAccount(Money money, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, LocalDate creationDate) {
        super(money, secretKey, primaryOwner, secondaryOwner);
        this.creationDate = creationDate;
    }

    public CheckingAccount() {

    }

    public void applyMonthlyMaintFee() {

        LocalDate now = LocalDate.now();

        LocalDate lastchecked = this.getLastChecked();

        Period timePassed = Period.between(lastchecked, now);
        int years = timePassed.getYears();
        int months = timePassed.getMonths();

        int monthTotal = months + (years * 12);

        for (int i = 0; i < monthTotal; i++) {
            this.getMoney().setAmount(this.getMoney().getAmount().subtract(monthlyMaintFee.getAmount()));

            if (this.getMoney().getAmount().compareTo(BigDecimal.valueOf(0)) < 0) {
                applyPenaltyFee();
            }
        }

        this.setLastChecked(now);
    }


    //getters
    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public Money getMonthlyMaintFee() {
        return monthlyMaintFee;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Status getStatus() {
        return status;
    }

    //setters
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
