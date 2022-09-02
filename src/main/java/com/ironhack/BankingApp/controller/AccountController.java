package com.ironhack.BankingApp.controller;

import com.ironhack.BankingApp.dtos.AccountDTO;
import com.ironhack.BankingApp.services.AccountService;
import com.ironhack.BankingApp.services.CheckingAccountService;
import com.ironhack.BankingApp.services.CreditCardService;
import com.ironhack.BankingApp.services.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/create/{accountType}/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@PathVariable String accountType, @PathVariable String id, @RequestBody AccountDTO accountDTO) {

        accountService.createAccount(accountType, id, accountDTO);

    }

    @DeleteMapping("/delete/account")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@RequestParam Long id) {

        accountService.deleteAccount(id);

    }
}
