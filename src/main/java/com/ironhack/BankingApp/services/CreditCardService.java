package com.ironhack.BankingApp.services;

import com.ironhack.BankingApp.dtos.AccountDTO;
import com.ironhack.BankingApp.models.accounts.CreditCard;
import com.ironhack.BankingApp.models.utilities.Money;
import com.ironhack.BankingApp.repositories.CreditCardRepository;
import com.ironhack.BankingApp.repositories.users.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;

@Service
public class CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public void createCredit(AccountDTO accountDTO, String id) {
        if (accountHolderRepository.findById(id).isPresent()) {

            CreditCard creditCard = new CreditCard();

            creditCard.setMoney(new Money(accountDTO.getCurrency(), accountDTO.getBalance()));
            creditCard.setSecretKey(accountDTO.getSecretKey());
            creditCard.setPrimaryOwner(accountHolderRepository.findById(id).get());
            if (accountDTO.getSecondaryOwner() != null) {
                creditCard.setSecondaryOwner(accountDTO.getSecondaryOwner());}
            if (accountDTO.getInterestRate() == null) {

            } else {
                creditCard.setInterestRate(accountDTO.getInterestRate());
            }
            creditCard.getCreditLimit().setAmount(accountDTO.getCreditLimit());

            creditCardRepository.save(creditCard);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "An AccountHolder with this ID doesn't exits in the database");
        }
    }

}
