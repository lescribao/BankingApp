package com.ironhack.BankingApp.services;

import com.ironhack.BankingApp.dtos.AccountDTO;
import com.ironhack.BankingApp.models.accounts.Account;
import com.ironhack.BankingApp.models.accounts.CheckingAccount;
import com.ironhack.BankingApp.models.accounts.SavingsAccount;
import com.ironhack.BankingApp.models.accounts.StudentChecking;
import com.ironhack.BankingApp.models.users.AccountHolder;
import com.ironhack.BankingApp.models.utilities.Money;
import com.ironhack.BankingApp.repositories.CheckingAccountRepository;
import com.ironhack.BankingApp.repositories.StudentCheckingRepository;
import com.ironhack.BankingApp.repositories.users.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class CheckingAccountService {

    @Autowired
    CheckingAccountRepository checkingAccountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    public void createChecking(AccountDTO accountDTO, String id) {
        if (accountHolderRepository.findById(id).isPresent()) {

            LocalDate today = LocalDate.now();

            Money money = new Money(accountDTO.getCurrency(), accountDTO.getBalance());
            String secretKey = accountDTO.getSecretKey();
            AccountHolder owner = accountHolderRepository.findById(id).get();
            AccountHolder secondaryOwner = accountDTO.getSecondaryOwner();
            Money minimumBalance = new Money(accountDTO.getMinimumBalance());
            LocalDate creationDate;
            if (accountDTO.getCreationDate() != null) {
                creationDate = accountDTO.getCreationDate();
            } else {
                creationDate = today;}

            if (accountHolderRepository.findById(id).get().getDateOfBirth().isAfter(today.minusYears(24))) {

                StudentChecking studentChecking = new StudentChecking(money, secretKey, owner, secondaryOwner, creationDate);

                studentCheckingRepository.save(studentChecking);

            } else {

                CheckingAccount checkingAccount = new CheckingAccount(money, secretKey, owner, secondaryOwner, creationDate);
                if (minimumBalance.getAmount() != null) {
                    checkingAccount.getMoney().setAmount(minimumBalance.getAmount());
                }

                checkingAccountRepository.save(checkingAccount);
            }

        } else {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "An AccountHolder with this ID doesn't exits in the database");
        }
    }
}




