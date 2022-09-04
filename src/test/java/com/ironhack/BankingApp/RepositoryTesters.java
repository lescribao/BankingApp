package com.ironhack.BankingApp;

import com.ironhack.BankingApp.models.users.ThirdParty;
import com.ironhack.BankingApp.repositories.ThirdPartyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RepositoryTesters {

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @BeforeEach
    void buildUp() {
        ThirdParty thirdParty = new ThirdParty("Jaume", "pasword", "123B-A23C");
        thirdPartyRepository.save(thirdParty);
    }

    @AfterEach
    void tearDown() {
        thirdPartyRepository.deleteAll();
    }

    @Test
    void findThirdPartyBySecretKey() {
        ThirdParty thirdParty = thirdPartyRepository.findBySecretKey("123B-A23C").get();

        assertEquals(thirdParty.getSecretKey(), "123B-A23C");

    }
}
