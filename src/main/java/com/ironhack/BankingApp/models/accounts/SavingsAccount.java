package com.ironhack.BankingApp.models.accounts;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ironhack.BankingApp.models.accounts.enums.Status;
import com.ironhack.BankingApp.models.users.AccountHolder;
import com.ironhack.BankingApp.models.utilities.Money;
import com.ironhack.BankingApp.models.utilities.enums.Currency;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class SavingsAccount extends Account{



    @NotNull
    @JsonFormat(pattern = "DD-MM-YYYY")
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Column(precision = 19, scale = 4)
    private BigDecimal interestRate = new BigDecimal(0.0025, new MathContext(4));

    @Column(precision = 19, scale = 4)
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "MINIMUM_AMOUNT")),
            @AttributeOverride(name = "currency", column = @Column(name = "MINIMUM_CURRENCY"))
    })
    private Money minimumBalance = new Money(Currency.EURO, BigDecimal.valueOf(1000.00));


    //constructors
    public SavingsAccount(Money money, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money minimumBalance, LocalDate creationDate, BigDecimal interestRate) {
        super(money, secretKey, primaryOwner, secondaryOwner);
        this.minimumBalance = minimumBalance;
        this.creationDate = creationDate;
        this.interestRate = interestRate;
    }

    public SavingsAccount() {
    }

    //method to add the interest to the savings account
    public void addInterestRate() {

        LocalDate now = LocalDate.now();
        LocalDate lastchecked = this.getLastChecked();

        Period timePassed = Period.between(lastchecked, now);
        int years = timePassed.getYears();

        for (int i = 0; i < years; i++) {
            this.getMoney().setAmount(this.getMoney().getAmount().add(this.getMoney().getAmount().multiply(this.getInterestRate())));
        }

        this.setLastChecked(now);

    }

    //getters
    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    //setters
    public void setMinimumBalance(BigDecimal minimumBalance) {

        if (minimumBalance.compareTo(BigDecimal.valueOf(100)) < 0) {

            this.minimumBalance.setCurrency(this.getMoney().getCurrency());
            this.minimumBalance.setAmount(BigDecimal.valueOf(100));

        } else {

            this.minimumBalance.setCurrency(this.getMinimumBalance().getCurrency());
            this.minimumBalance.setAmount(minimumBalance);

        }
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setInterestRate(BigDecimal interestRate) {

        if(interestRate.compareTo(BigDecimal.valueOf(0.5)) > 0) {

            this.interestRate = BigDecimal.valueOf(0.5);

        } else if (interestRate.compareTo(BigDecimal.valueOf(0)) < 0) {

            this.interestRate = BigDecimal.valueOf(0);

        } else if (interestRate == null){

            this.interestRate = interestRate;

        }
    }
}
