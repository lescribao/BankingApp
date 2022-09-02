package com.ironhack.BankingApp.controller;

import com.ironhack.BankingApp.models.users.ThirdParty;
import com.ironhack.BankingApp.services.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class ThirdPartyController {

    @Autowired
    ThirdPartyService thirdPartyService;

    @PostMapping("/newThirdParty/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createThirdParty(@RequestBody ThirdParty thirdParty) {

        thirdPartyService.createThirdParty(thirdParty);

    }

    @PostMapping("/ThirdParty/send/")
    @ResponseStatus(HttpStatus.OK)
    public void thirdPartySendMoney(@RequestHeader("hashedKey") String hashedKey ,
                                    @RequestHeader("amount") BigDecimal amount,
                                    @RequestHeader("accountId") Long accountId,
                                    @RequestHeader("secretKey") String secretKey) {

        thirdPartyService.sendMoney(hashedKey, amount, accountId, secretKey);

    }
}
