package com.ironhack.BankingApp.controller;

import com.ironhack.BankingApp.models.users.AccountHolder;
import com.ironhack.BankingApp.repositories.users.AdminRepository;
import com.ironhack.BankingApp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @PatchMapping("/modifyBalance/{amount}")
    @ResponseStatus(HttpStatus.OK)
    public void modifyBalance(@PathVariable BigDecimal amount , @RequestParam Long id) {

        adminService.modifyBalance(id, amount);

    }

    @PatchMapping("/FreezeAccount/")
    @ResponseStatus(HttpStatus.OK)
    public void freezeAccount(@RequestParam Long id) {

        adminService.freezeAccount(id);
    }
}
