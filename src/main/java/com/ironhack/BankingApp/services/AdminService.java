package com.ironhack.BankingApp.services;

import com.ironhack.BankingApp.models.accounts.*;
import com.ironhack.BankingApp.models.accounts.enums.Status;
import com.ironhack.BankingApp.models.users.AccountHolder;
import com.ironhack.BankingApp.repositories.AccountRepository;
import com.ironhack.BankingApp.repositories.users.AccountHolderRepository;
import com.ironhack.BankingApp.repositories.users.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.*;
import java.math.BigDecimal;

@Service
public class AdminService {

    @Autowired
    AccountRepository accountRepository;


    public void modifyBalance(Long id, BigDecimal amount) {

        if (accountRepository.findById(id).isPresent()) {
            Account account = accountRepository.findById(id).get();

            account.getMoney().setAmount(amount);
            accountRepository.save(account);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The ID introduced doesn't match any Accounts in our systems.");
        }

    }

    public void freezeAccount(Long id) {
        if (accountRepository.findById(id).isPresent()) {

            Account account = accountRepository.findById(id).get();

            if (account.getClass().equals(SavingsAccount.class)) {
                ((SavingsAccount) account).setStatus(Status.FROZEN);
            } else if (account.getClass().equals(StudentChecking.class)) {
                ((StudentChecking) account).setStatus(Status.FROZEN);
            } else if (account.getClass().equals(CheckingAccount.class)) {
                ((CheckingAccount) account).setStatus(Status.FROZEN);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't Freeze a CreditCard Account type.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with the introduced ID not found.");
        }
    }
}
