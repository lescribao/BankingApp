package com.ironhack.BankingApp.models.accounts;

import com.ironhack.BankingApp.models.users.AccountHolder;
import com.ironhack.BankingApp.models.utilities.Money;
import com.ironhack.BankingApp.models.utilities.enums.Currency;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class CreditCard extends Account{

    @Column(precision = 19, scale = 4)
    private BigDecimal interestRate = new BigDecimal(0.2, new MathContext(4));

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "CREDIT_AMOUNT")),
            @AttributeOverride(name = "currency", column = @Column(name = "CREDIT_CURRENCY"))
    })
    private Money creditLimit = new Money(Currency.EURO, BigDecimal.valueOf(100.00));

    //constructors
    public CreditCard(Money money, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal interestRate, Money creditLimit) {
        super(money, secretKey, primaryOwner, secondaryOwner);
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
    }

    public CreditCard() {
    }

    //method to add the interest to the creditCard
    public void addInterestRate() {

        LocalDate now = LocalDate.now();
        LocalDate lastchecked = this.getLastChecked();

        Period timePassed = Period.between(lastchecked, now);
        int years = timePassed.getYears();
        int months = timePassed.getMonths();

        int monthTotal = months + (years * 12);

        for (int i = 0; i < monthTotal; i++) {
            this.getMoney().setAmount(this.getMoney().getAmount().add(this.getMoney().getAmount().multiply(this.getInterestRate())));
        }

        this.setLastChecked(now);
    }

    //getters
    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    //setters
    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(BigDecimal.valueOf(0.1)) < 0) {
            this.interestRate = BigDecimal.valueOf(0.1);
        } else {
            this.interestRate = interestRate;
        }

    }

    public void setCreditLimit(Money creditLimit) {
        if (creditLimit.getAmount().compareTo(BigDecimal.valueOf(100000)) > 0) {
            this.creditLimit.setAmount(BigDecimal.valueOf(100000));
        } else {
            this.creditLimit = creditLimit;
        }
    }
}
