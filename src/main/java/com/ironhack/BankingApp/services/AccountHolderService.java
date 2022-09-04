package com.ironhack.BankingApp.services;

import com.ironhack.BankingApp.models.accounts.*;
import com.ironhack.BankingApp.models.users.AccountHolder;
import com.ironhack.BankingApp.models.utilities.Money;
import com.ironhack.BankingApp.models.utilities.Transference;
import com.ironhack.BankingApp.repositories.AccountRepository;
import com.ironhack.BankingApp.repositories.TransferenceRepository;
import com.ironhack.BankingApp.repositories.users.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transaction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountHolderService {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransferenceRepository transferenceRepository;

    public void transferAmount(String ownerName, Long ownerId, Money transactionAmount) {

        Account receiverAccount;
        AccountHolder receiverHolder;

        if (accountRepository.findById(ownerId).isPresent()) {
            receiverAccount = accountRepository.findById(ownerId).get();

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The ID of the account is not in our systems");
        }

        if (receiverAccount.getPrimaryOwner().getUsername().matches(ownerName) || receiverAccount.getSecondaryOwner().getUsername().matches(ownerName)) {

            //get the _sender's_ information to find him through the basic auth

            Account senderAccount = new CheckingAccount(); //youknowwhat

            receiverHolder = receiverAccount.getPrimaryOwner();



            if (senderAccount.getMoney().getAmount().compareTo(transactionAmount.getAmount()) < 0) {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not enough funds in your account");
            } else {

                //We grab all three values for the transaction, the amount being sent, the balance of the sender and reciever.
                //And we convert them to a single local currency, in this case, euro.
                BigDecimal amountSent = transactionAmount.moneyConversionEur();
                BigDecimal senderBalance = senderAccount.getMoney().moneyConversionEur();
                BigDecimal receiverBalance = receiverAccount.getMoney().moneyConversionEur();

                //we subtract and add the amountSent to each respective Balance.
                BigDecimal senderFinal = senderBalance.subtract(amountSent);
                BigDecimal receiverFinal = receiverBalance.add(amountSent);


                //and once again we make a Money class to return it to the currency type and save it onto the Accounts
                Money resultSender = senderAccount.getMoney();
                Money resultReceiver = receiverAccount.getMoney();

                //we set the amount, currency of the account and convert the values back onto it's original currency
                resultSender.setAmount(senderFinal);
                resultSender.conversionToAccount();

                resultReceiver.setAmount(receiverFinal);
                resultReceiver.conversionToAccount();

                //and we set said Money Class onto the Accounts
                senderAccount.setMoney(resultSender);
                receiverAccount.setMoney(resultReceiver);

                //we check if the sender account is a checking or savings account, and if so, if the transference
                //puts them under the minimumBalance mark, if so, we apply the penalty.
                if (senderAccount.getClass().equals(CheckingAccount.class)) {
                    if (senderAccount.getMoney().getAmount().compareTo(((CheckingAccount) senderAccount).getMinimumBalance().getAmount()) < 0) {
                        senderAccount.applyPenaltyFee();
                    }
                } else if (senderAccount.getClass().equals(SavingsAccount.class)){
                    if (senderAccount.getMoney().getAmount().compareTo(((SavingsAccount) senderAccount).getMinimumBalance().getAmount()) < 0) {
                        senderAccount.applyPenaltyFee();
                    }
                }


                //and finally we save them back to the repositories
                accountRepository.save(senderAccount);
                accountRepository.save(receiverAccount);



                //and add the transference to the transference lists onto the account owners.

                Transference transference = new Transference(senderAccount.getPrimaryOwner(), receiverAccount.getPrimaryOwner());
                transferenceRepository.save(transference);

            }

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name of the client introduced doesn't match");
        }

    }

    public Account checkAccountById(Long id, String username) {
        if (accountHolderRepository.findById(username).isPresent()) {

            AccountHolder accountHolder = accountHolderRepository.findById(username).get();

            for (int i = 0; i < accountHolder.getPrimaryAccountList().size(); i++) {
                if (accountRepository.findById(id).equals(accountHolder.getPrimaryAccountList().get(i))) {
                    if (accountHolder.getPrimaryAccountList().get(i).getClass() == CheckingAccount.class) {

                        CheckingAccount account = (CheckingAccount) accountHolder.getPrimaryAccountList().get(i);

                        account.applyMonthlyMaintFee();
                        accountRepository.save(account);

                        return account;

                    }
                    else if (accountHolder.getPrimaryAccountList().get(i).getClass() == SavingsAccount.class) {

                        SavingsAccount account = (SavingsAccount) accountHolder.getPrimaryAccountList().get(i);

                        account.addInterestRate();
                        accountRepository.save(account);

                        return account;

                    }
                    else if (accountHolder.getPrimaryAccountList().get(i).getClass() == CreditCard.class) {

                        CreditCard account = (CreditCard) accountHolder.getPrimaryAccountList().get(i);

                        account.addInterestRate();
                        accountRepository.save(account);

                        return account;

                    }
                    else {

                        return accountHolder.getPrimaryAccountList().get(i);

                    }
                }
            }
            for (int j = 0; j < accountHolder.getSndAccountList().size(); j++) {
                if (accountRepository.findById(id).equals(accountHolder.getSndAccountList().get(j))) {
                    if (accountHolder.getSndAccountList().get(j).getClass() == CheckingAccount.class) {

                        CheckingAccount account = (CheckingAccount) accountHolder.getSndAccountList().get(j);

                        account.applyMonthlyMaintFee();
                        accountRepository.save(account);

                        return account;

                    }
                    else if (accountHolder.getSndAccountList().get(j).getClass() == SavingsAccount.class) {

                        SavingsAccount account = (SavingsAccount) accountHolder.getSndAccountList().get(j);

                        account.addInterestRate();
                        accountRepository.save(account);

                        return account;

                    }
                    else if (accountHolder.getSndAccountList().get(j).getClass() == CreditCard.class) {

                        CreditCard account = (CreditCard) accountHolder.getSndAccountList().get(j);

                        account.addInterestRate();
                        accountRepository.save(account);

                        return account;

                    }
                    else {

                        return accountHolder.getSndAccountList().get(j);

                    }
                }
            }

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The ID introduced doesn't match any of the banking accounts" +
                    "to your name");

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The username used to log-in doesn't match the any AccountHolders in our database.");
        }


    }

    public List<Account> checkAccounts(String username) {

       if (accountHolderRepository.findById(username).isPresent()) {

           List<Account> accountList = new ArrayList<>();

           AccountHolder accountHolder = new AccountHolder(); //accountHolderRepository.findById().get();


           for (int i = 0; i < accountHolder.getPrimaryAccountList().size(); i++) {
               if (accountHolder.getPrimaryAccountList().get(i).getClass() == CheckingAccount.class) {

                   CheckingAccount account = (CheckingAccount) accountHolder.getPrimaryAccountList().get(i);

                   account.applyMonthlyMaintFee();
                   accountRepository.save(account);

                   accountList.add(account);

               }
               else if (accountHolder.getPrimaryAccountList().get(i).getClass() == SavingsAccount.class) {

                   SavingsAccount account = (SavingsAccount) accountHolder.getPrimaryAccountList().get(i);

                   account.addInterestRate();
                   accountRepository.save(account);

                   accountList.add(account);

               }
               else if (accountHolder.getPrimaryAccountList().get(i).getClass() == CreditCard.class) {

                   CreditCard account = (CreditCard) accountHolder.getPrimaryAccountList().get(i);

                   account.addInterestRate();
                   accountRepository.save(account);

                   accountList.add(account);

               }
               else {



               }
           }

           for (int i = 0; i < accountHolder.getSndAccountList().size(); i++) {
               if (accountHolder.getSndAccountList().get(i).getClass() == CheckingAccount.class) {

                   CheckingAccount account = (CheckingAccount) accountHolder.getSndAccountList().get(i);

                   account.applyMonthlyMaintFee();
                   accountRepository.save(account);

                   accountList.add(account);

               }
               else if (accountHolder.getSndAccountList().get(i).getClass() == SavingsAccount.class) {

                   SavingsAccount account = (SavingsAccount) accountHolder.getSndAccountList().get(i);

                   account.addInterestRate();
                   accountRepository.save(account);

                   accountList.add(account);

               }
               else if (accountHolder.getSndAccountList().get(i).getClass() == CreditCard.class) {

                   CreditCard account = (CreditCard) accountHolder.getSndAccountList().get(i);

                   account.addInterestRate();
                   accountRepository.save(account);

                   accountList.add(account);

               }
               else {

               }
           }

           return accountList;
       }
       else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The username introduced doesn't match any accounts");
        }

    }
}
