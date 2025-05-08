package org.example.digital_banking_backend.services;

import org.example.digital_banking_backend.dtos.CustomerDTO;
import org.example.digital_banking_backend.entities.BankAccount;
import org.example.digital_banking_backend.entities.CurrentAccount;
import org.example.digital_banking_backend.entities.Customer;
import org.example.digital_banking_backend.entities.SavingAccount;
import org.example.digital_banking_backend.exceptions.BalanceNotEnoughException;
import org.example.digital_banking_backend.exceptions.BankAccountNotFoundException;
import org.example.digital_banking_backend.exceptions.CustomerNotFoundException;
import java.util.List;

public interface BankAccountService {

    Customer saveCustomer(Customer customer);

    CurrentAccount saveCurrentBankAccount(double initialBalance, Long customerId, double overDraft) throws CustomerNotFoundException;

    SavingAccount saveSavingBankAccount(double initialBalance, Long customerId, double interestRate) throws CustomerNotFoundException;

    List<CustomerDTO> listCustomers();

    BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException;

    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotEnoughException;

    void credit(String accountId, double amount, String description) throws BalanceNotEnoughException, BankAccountNotFoundException;

    void transfer(String fromAccId, String toAccId, double amount) throws BalanceNotEnoughException, BankAccountNotFoundException;

    List<BankAccount> bankAccountList();

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;
}

