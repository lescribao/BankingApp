package com.ironhack.BankingApp.services;

import com.ironhack.BankingApp.dtos.AccountDTO;
import com.ironhack.BankingApp.models.accounts.CreditCard;
import com.ironhack.BankingApp.models.accounts.SavingsAccount;
import com.ironhack.BankingApp.models.utilities.Money;
import com.ironhack.BankingApp.repositories.SavingsAccountRepository;
import com.ironhack.BankingApp.repositories.users.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SavingsAccountService {

    @Autowired
    SavingsAccountRepository savingsAccountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;


    public void createSavings(AccountDTO accountDTO, String id) {
        if (accountHolderRepository.findById(id).isPresent()) {

            SavingsAccount savingsAccount = new SavingsAccount();
            /*
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Missing parameters to properly create a Savings Account");
            }
            */

            savingsAccount.setMoney(new Money(accountDTO.getCurrency(), accountDTO.getBalance()));
            savingsAccount.setSecretKey(accountDTO.getSecretKey());
            savingsAccount.setPrimaryOwner(accountHolderRepository.findById(id).get());
            if (accountDTO.getSecondaryOwner() != null) {
                savingsAccount.setSecondaryOwner(accountDTO.getSecondaryOwner());}
            if (accountDTO.getMinimumBalance() != null) {
                savingsAccount.setMinimumBalance(accountDTO.getMinimumBalance());
            }
            savingsAccount.setCreationDate(accountDTO.getCreationDate());
            if (accountDTO.getInterestRate() == null) {

            } else {
                savingsAccount.setInterestRate(accountDTO.getInterestRate());
            }


            savingsAccountRepository.save(savingsAccount);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "An AccountHolder with this ID doesn't exits in the database");
        }
    }
}
