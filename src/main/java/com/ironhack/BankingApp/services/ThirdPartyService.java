package com.ironhack.BankingApp.services;

import com.ironhack.BankingApp.models.accounts.Account;
import com.ironhack.BankingApp.models.users.ThirdParty;
import com.ironhack.BankingApp.repositories.AccountRepository;
import com.ironhack.BankingApp.repositories.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class ThirdPartyService {

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @Autowired
    AccountRepository accountRepository;

    public void createThirdParty(ThirdParty thirdParty) {

        thirdPartyRepository.save(thirdParty);

    }

    public void sendMoney(String hashedKey, BigDecimal amount, Long accountId, String secretKey) {

        //we check if the hashed Key is valid, this process would be made in a real environment where we could
        //interact with other banks, and check if the hashedKey corresponds with an account of a third party bank.

        //since it's an outside bank, the process to check if there's enough currency to do the transference

        if (accountRepository.findById(accountId).isPresent()) {
            if (accountRepository.findById(accountId).get().getSecretKey().matches(secretKey)) {

                Account account = accountRepository.findById(accountId).get();

                account.getMoney().setAmount(account.getMoney().getAmount().add(amount));
                accountRepository.save(account);

            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The secretKey doesn't match the one " +
                        "of the account with the given ID");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The ID provided doesn't match any accounts in our" +
                    "banking system.");
        }

    }


}
