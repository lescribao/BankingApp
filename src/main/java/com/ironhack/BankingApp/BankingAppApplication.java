package com.ironhack.BankingApp;


import com.ironhack.BankingApp.models.accounts.CheckingAccount;
import com.ironhack.BankingApp.models.accounts.enums.Status;
import com.ironhack.BankingApp.models.users.AccountHolder;
import com.ironhack.BankingApp.models.users.Admin;
import com.ironhack.BankingApp.models.users.Role;
import com.ironhack.BankingApp.models.users.ThirdParty;
import com.ironhack.BankingApp.models.utilities.Address;
import com.ironhack.BankingApp.models.utilities.Money;
import com.ironhack.BankingApp.models.utilities.enums.Currency;
import com.ironhack.BankingApp.repositories.ThirdPartyRepository;
import com.ironhack.BankingApp.repositories.users.AccountHolderRepository;
import com.ironhack.BankingApp.repositories.users.AdminRepository;
import com.ironhack.BankingApp.repositories.users.RoleRepository;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

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

		Money money = new Money(Currency.EURO, new BigDecimal("123.00"));
		LocalDate date = LocalDate.of(2000, 11, 11);
		AccountHolder accountHolder = new AccountHolder("user", passwordEnconder.encode("pass"), date, new Address(), new Address());
		CheckingAccount acc = new CheckingAccount(money, "thisisakey", accountHolder, null, date, Status.ACTIVE);

*/


	}

	@Override
	public void run(String... args) throws Exception {


		adminRepository.deleteAll();
		roleRepository.deleteAll();
		thirdPartyRepository.deleteAll();
		accountHolderRepository.deleteAll();

		Admin admin = adminRepository.save(new Admin("Marc", passwordEncoder.encode("1234")));
		admin.addRole(new Role("ADMIN", admin));
		adminRepository.save(admin);

		ThirdParty thirdParty = new ThirdParty("Laia", passwordEncoder.encode("password"), "AB234102");
		thirdParty.addRole(new Role("THIRD_PARTY", thirdParty));
		thirdPartyRepository.save(thirdParty);

		LocalDate dob = LocalDate.of(2000, 9, 11);
		Address address = new Address("Calle Numancia 13", 0000, "Barcelona", "España");
		AccountHolder accountHolder = new AccountHolder("Nas", passwordEncoder.encode("4444"), dob, address, null);
		accountHolder.addRole(new Role("ACCOUNT_HOLDER", accountHolder));

		accountHolderRepository.save(accountHolder);
		adminRepository.save(admin);
		thirdPartyRepository.save(thirdParty);



	}


	//Here is our second solution to this problem using the Joda-Time library. This time, we are using the LocalDate class, similar to LocalDate of JDK 8, which also represents just date without any time component.

	//Once you have the LocalDate, you can use Months.monthsBetween() and Years.yearsBetween() method to calcualte the number of months and years between two dates in Java.

	//Read more: https://javarevisited.blogspot.com/2016/10/how-to-get-number-of-months-and-years-between-two-dates-in-java.html#ixzz7dZx6VDrT

}
