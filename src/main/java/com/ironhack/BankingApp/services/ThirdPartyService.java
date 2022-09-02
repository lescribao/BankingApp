package com.ironhack.BankingApp.services;

import com.ironhack.BankingApp.models.users.ThirdParty;
import com.ironhack.BankingApp.repositories.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ThirdPartyService {

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    public void createThirdParty(ThirdParty thirdParty) {

        thirdPartyRepository.save(thirdParty);

    }

    public void sendMoney(String hashedKey, BigDecimal amount, String accountId) {



    }


}
