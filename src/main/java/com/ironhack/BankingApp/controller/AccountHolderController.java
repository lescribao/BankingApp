package com.ironhack.BankingApp.controller;

import com.ironhack.BankingApp.models.accounts.Account;
import com.ironhack.BankingApp.models.users.AccountHolder;
import com.ironhack.BankingApp.models.utilities.Money;
import com.ironhack.BankingApp.services.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class AccountHolderController {

    @Autowired
    AccountHolderService accountHolderService;

    @GetMapping("/accounts/")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> checkAccounts(@AuthenticationPrincipal UserDetails userDetails) {

        //somehow have to check if the username in basic auth is valid,
        // and return it here so it can be used in the service.

        return accountHolderService.checkAccounts(userDetails.getUsername());
    }

    @GetMapping("/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account checkAccountById(@PathVariable Long id,  @AuthenticationPrincipal UserDetails userDetails) {

        return accountHolderService.checkAccountById(id, userDetails.getUsername());

    }
}


