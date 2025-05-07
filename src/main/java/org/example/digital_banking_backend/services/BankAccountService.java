package org.example.digital_banking_backend.services;

import org.example.digital_banking_backend.entities.BankAccount;
import org.example.digital_banking_backend.entities.CurrentAccount;
import org.example.digital_banking_backend.entities.Customer;
import org.example.digital_banking_backend.entities.SavingAccount;
import org.example.digital_banking_backend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {

    Customer saveCustomer(Customer customer);

    CurrentAccount saveCurrentBankAccount(double initialBalance, Long customerId, double overDraft) throws CustomerNotFoundException;

    SavingAccount saveSavingBankAccount(double initialBalance, Long customerId, double interestRate) throws CustomerNotFoundException;

    List<Customer> listCustomers();

    BankAccount getBankAccount(String accountId);

    void debit(String accountId, double amount, String description);

    void credit(String accountId, double amount, String description);

    void transfer(String fromAccId, String toAccId, double amount);
}

