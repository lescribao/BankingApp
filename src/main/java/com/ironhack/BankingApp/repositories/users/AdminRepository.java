package com.ironhack.BankingApp.repositories.users;

import com.ironhack.BankingApp.models.users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
}
