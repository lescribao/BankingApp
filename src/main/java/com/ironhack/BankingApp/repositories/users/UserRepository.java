package com.ironhack.BankingApp.repositories.users;

import com.ironhack.BankingApp.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
