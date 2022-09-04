package com.ironhack.BankingApp.repositories;

import com.ironhack.BankingApp.models.users.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty, String> {

    public Optional<ThirdParty> findBySecretKey (String secretKey);
}
