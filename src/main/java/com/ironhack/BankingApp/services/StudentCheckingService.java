package com.ironhack.BankingApp.services;

import com.ironhack.BankingApp.models.accounts.StudentChecking;
import com.ironhack.BankingApp.repositories.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCheckingService {

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

}
