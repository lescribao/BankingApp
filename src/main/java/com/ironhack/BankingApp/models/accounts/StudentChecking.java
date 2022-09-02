package com.ironhack.BankingApp.models.accounts;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ironhack.BankingApp.models.accounts.enums.Status;
import com.ironhack.BankingApp.models.users.AccountHolder;
import com.ironhack.BankingApp.models.utilities.Money;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class StudentChecking extends Account{

    @NotNull
    @JsonFormat(pattern = "DD-MM-YYYY")
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    //constructor
    public StudentChecking(Money money, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, LocalDate creationDate) {
        super(money, secretKey, primaryOwner, secondaryOwner);
        this.creationDate = creationDate;
    }

    public StudentChecking() {
    }

    //getters
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
