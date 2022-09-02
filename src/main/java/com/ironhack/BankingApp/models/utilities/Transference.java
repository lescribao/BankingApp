package com.ironhack.BankingApp.models.utilities;

import com.ironhack.BankingApp.models.users.AccountHolder;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Transference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn (name = "sentTransferenceList")
    AccountHolder moneySender;

    @ManyToOne
    @JoinColumn (name = "receiveTransferenceList")
    AccountHolder moneyReceiver;

    private final LocalDate transactionDate = LocalDate.now();

    //constructor
    public Transference(AccountHolder moneySender, AccountHolder moneyReceiver) {

        this.moneySender = moneySender;
        this.moneyReceiver = moneyReceiver;
    }

    public Transference() {

    }



    //getters
    public Long getId() {
        return id;
    }

    public AccountHolder getMoneySender() {
        return moneySender;
    }

    public AccountHolder getMoneyReceiver() {
        return moneyReceiver;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    //setters

    public void setMoneySender(AccountHolder moneySender) {
        this.moneySender = moneySender;
    }

    public void setMoneyReceiver(AccountHolder moneyReceiver) {
        this.moneyReceiver = moneyReceiver;
    }
}
