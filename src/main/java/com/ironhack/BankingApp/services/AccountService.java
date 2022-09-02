package com.ironhack.BankingApp.services;

import com.ironhack.BankingApp.dtos.AccountDTO;
import com.ironhack.BankingApp.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AccountService {

    @Autowired
    CheckingAccountService checkingAccountService;

    @Autowired
    SavingsAccountService savingsAccountService;

    @Autowired
    CreditCardService creditCardService;

    @Autowired
    AccountRepository accountRepository;

    public void createAccount(String accountType, String id, AccountDTO accountDTO) {

        switch (accountType) {
            case "checking" -> {
                checkingAccountService.createChecking(accountDTO, id);
                break;
            }
            case "savings" -> {
                savingsAccountService.createSavings(accountDTO, id);
                break;
            }
            case "credit" -> {
                creditCardService.createCredit(accountDTO, id);
                break;
            }
            default -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account type is not valid, please re-try.");
            }

        }
    }

    public void deleteAccount(Long id) {

        if (accountRepository.findById(id).isPresent()) {

            accountRepository.delete(accountRepository.findById(id).get());

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The ID doesn't match any of the accounts in our system");
        }

    }
}
