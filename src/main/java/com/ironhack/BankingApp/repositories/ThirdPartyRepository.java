package com.ironhack.BankingApp.repositories;

import com.ironhack.BankingApp.models.users.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty, String> {

}
