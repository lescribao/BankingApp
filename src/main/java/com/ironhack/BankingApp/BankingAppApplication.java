package com.ironhack.BankingApp;

import ch.qos.logback.classic.pattern.LineOfCallerConverter;
import com.ironhack.BankingApp.models.accounts.Account;
import com.ironhack.BankingApp.models.accounts.CheckingAccount;
import com.ironhack.BankingApp.models.accounts.enums.Status;
import com.ironhack.BankingApp.models.users.*;
import com.ironhack.BankingApp.models.utilities.Address;
import com.ironhack.BankingApp.models.utilities.Money;
import com.ironhack.BankingApp.models.utilities.enums.Currency;
import com.ironhack.BankingApp.repositories.ThirdPartyRepository;
import com.ironhack.BankingApp.repositories.users.AccountHolderRepository;
import com.ironhack.BankingApp.repositories.users.AdminRepository;
import com.ironhack.BankingApp.repositories.users.RoleRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

@SpringBootApplication
public class BankingAppApplication implements CommandLineRunner  {


	@Autowired
	AccountHolderRepository accountHolderRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	ThirdPartyRepository thirdPartyRepository;

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


	public static void main(String[] args) {
		SpringApplication.run(BankingAppApplication.class, args);


/*
		Money money = new Money(Currency.EURO, new BigDecimal(123.00));
		LocalDate date = LocalDate.of(2000, 11, 11);
		AccountHolder accountHolder = new AccountHolder("user", "pass", date, new Address(), new Address());
		CheckingAccount acc = new CheckingAccount(money, "thisisakey", accountHolder, null, date, Status.ACTIVE);
 */



	}

	@Override
	public void run(String... args) throws Exception {


/*

		Admin admin = new Admin("Jaume", passwordEncoder.encode("1234"));
		Role adminRole = new Role("ADMIN", admin);


		ThirdParty thirdParty = new ThirdParty("Laia", passwordEncoder.encode("password"), "AB234102");
		Role thirdPartyRole = new Role("THIRD_PARTY", thirdParty);

		LocalDate dob = LocalDate.of(2000, 9, 11);
		Address address = new Address("Calle Numancia 13", 0000, "Barcelona", "España");
		AccountHolder accountHolder = new AccountHolder("Nas", passwordEncoder.encode("4444"), dob, address, null);
		Role accountHolderRole = new Role("ACCOUNT_HOLDER", accountHolder);

		roleRepository.save(adminRole);
		roleRepository.save(thirdPartyRole);
		roleRepository.save(accountHolderRole);

		accountHolderRepository.save(accountHolder);
		adminRepository.save(admin);
		thirdPartyRepository.save(thirdParty);

 */




	}


	//Here is our second solution to this problem using the Joda-Time library. This time, we are using the LocalDate class, similar to LocalDate of JDK 8, which also represents just date without any time component.

	//Once you have the LocalDate, you can use Months.monthsBetween() and Years.yearsBetween() method to calcualte the number of months and years between two dates in Java.

	//Read more: https://javarevisited.blogspot.com/2016/10/how-to-get-number-of-months-and-years-between-two-dates-in-java.html#ixzz7dZx6VDrT

}
