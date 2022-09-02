package com.ironhack.BankingApp.models.utilities;

import com.ironhack.BankingApp.models.utilities.enums.Currency;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Embeddable
public class Money {

    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;

    private static Currency defaultCurrency = Currency.EURO;

    @NotNull
    private Currency currency;
    @NotNull
    @Column(precision = 19, scale = 4)
    private BigDecimal amount;


    //constructor
    public Money(Currency currency, BigDecimal amount) {
        setCurrency(currency);
        this.amount = amount;
    }

    public Money(BigDecimal amount) {
        this.amount = amount;
        this.currency = defaultCurrency;
    }

    public Money () {

    }

    public BigDecimal moneyConversionEur() {
        if (this.currency == Currency.EURO) {
            return getAmount();
        } else if (this.currency == Currency.USD) {
            return getAmount().multiply(BigDecimal.valueOf(1.02));
        } else {
            return getAmount().multiply(BigDecimal.valueOf(0.76));
        }
    }

    public BigDecimal conversionToAccount() {
        if (this.currency == Currency.EURO) {
            return getAmount();
        } else if (this.currency == Currency.USD) {
            return getAmount().multiply(BigDecimal.valueOf(0.98));
        } else {
            return getAmount().multiply(BigDecimal.valueOf(1.24));
        }
    }

    //getters
    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    //Setters
    public void setCurrency(Currency currency) {
        if (currency == null) {
            this.currency = defaultCurrency;
        } else {
            this.currency = currency;
        }
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
