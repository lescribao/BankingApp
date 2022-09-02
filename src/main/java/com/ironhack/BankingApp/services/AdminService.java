package com.ironhack.BankingApp.services;

import com.ironhack.BankingApp.models.accounts.Account;
import com.ironhack.BankingApp.models.users.AccountHolder;
import com.ironhack.BankingApp.repositories.AccountRepository;
import com.ironhack.BankingApp.repositories.users.AccountHolderRepository;
import com.ironhack.BankingApp.repositories.users.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The secret key introduced is not in our systems");
        }

    }
}
