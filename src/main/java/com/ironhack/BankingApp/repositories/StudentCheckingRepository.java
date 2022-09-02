package com.ironhack.BankingApp.repositories;

import com.ironhack.BankingApp.models.accounts.StudentChecking;
import com.ironhack.BankingApp.models.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingRepository extends JpaRepository<StudentChecking, AccountHolder> {

}
