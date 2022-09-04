package com.ironhack.BankingApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.BankingApp.models.accounts.SavingsAccount;
import com.ironhack.BankingApp.models.users.AccountHolder;
import com.ironhack.BankingApp.models.users.Admin;
import com.ironhack.BankingApp.models.users.Role;
import com.ironhack.BankingApp.models.utilities.Address;
import com.ironhack.BankingApp.models.utilities.Money;
import com.ironhack.BankingApp.repositories.AccountRepository;
import com.ironhack.BankingApp.repositories.SavingsAccountRepository;
import com.ironhack.BankingApp.repositories.users.AccountHolderRepository;
import com.ironhack.BankingApp.repositories.users.AdminRepository;
import com.ironhack.BankingApp.repositories.users.RoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccountTests {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    PasswordEncoder passwordEncoder;

    private MockMvc mockMvc;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    SavingsAccountRepository savingsAccountRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper.findAndRegisterModules();

        LocalDate dob = LocalDate.of(2000, 9, 11);
        Address address = new Address("Calle Numancia 13", 0000, "Barcelona", "España");
        AccountHolder accountHolder = new AccountHolder("Nas", passwordEncoder.encode("4444"), dob, address, null);
        Role accountHolderRole = new Role("ACCOUNT_HOLDER", accountHolder);

        Money money = new Money(new BigDecimal(200.00));
        Money minimumBal = new Money(new BigDecimal(150.00));
        BigDecimal interest = new BigDecimal(0.3);

        SavingsAccount savingsAccount = new SavingsAccount(money, "AB23192684", accountHolder, new AccountHolder(), minimumBal, LocalDate.now(), interest);
        savingsAccount.setId(15L);

        savingsAccountRepository.save(savingsAccount);
        accountHolderRepository.save(accountHolder);
        roleRepository.save(accountHolderRole);

    }

    @AfterEach
    void tearDown() {
        roleRepository.deleteAll();
        accountHolderRepository.deleteAll();
    }

    @Test
    void createAccountOk() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/create/{accountType}/{id}", "checking", "Nas").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();



    }

    void createAccountError() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/create/{accountType}/{id}", "checking").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();
        assertTrue(mvcResult.getResolvedException().getMessage().contains("An AccountHolder with this ID doesn't exits in the database"));
    }

    @Test
    void deleteAccountOk() throws Exception {

        MvcResult mvcResult = mockMvc.perform(delete("/delete/account?id=15")).andExpect(status().isOk()).andReturn();

        boolean found = savingsAccountRepository.findById(15L).isPresent();

        assertFalse(found);

    }
}
