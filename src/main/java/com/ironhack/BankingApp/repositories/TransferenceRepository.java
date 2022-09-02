package com.ironhack.BankingApp.repositories;

import com.ironhack.BankingApp.models.utilities.Transference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenceRepository extends JpaRepository<Transference, Long> {
}
