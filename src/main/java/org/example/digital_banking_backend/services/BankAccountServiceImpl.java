package org.example.digital_banking_backend.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.digital_banking_backend.entities.BankAccount;
import org.example.digital_banking_backend.entities.CurrentAccount;
import org.example.digital_banking_backend.entities.Customer;
import org.example.digital_banking_backend.entities.SavingAccount;
import org.example.digital_banking_backend.exceptions.CustomerNotFoundException;
import org.example.digital_banking_backend.repositories.AccountOperationRepository;
import org.example.digital_banking_backend.repositories.BankAccountRepository;
import org.example.digital_banking_backend.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {

    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;


    @Override
    public Customer saveCustomer(Customer customer) {

        log.info("Saving new customer");
        Customer savedCustomer = customerRepository.save(customer);

        return savedCustomer;
    }


    @Override
    public CurrentAccount saveCurrentBankAccount(double initialBalance, Long customerId, double overDraft) throws CustomerNotFoundException {

        CurrentAccount currentAcc = new CurrentAccount();
        Customer customer = customerRepository.findById(customerId).orElseThrow();

        if (customer == null)
            throw new CustomerNotFoundException("Customer not found");

        currentAcc.setId(UUID.randomUUID().toString());
        currentAcc.setCreatedAt(new Date());
        currentAcc.setCustomer(customer);
        currentAcc.setOverDraft(overDraft);
        currentAcc.setBalance(initialBalance);

        CurrentAccount savedAcc = bankAccountRepository.save(currentAcc);

        return savedAcc;
    }


    @Override
    public SavingAccount saveSavingBankAccount(double initialBalance, Long customerId, double interestRate) throws CustomerNotFoundException {

        SavingAccount savingAcc = new SavingAccount();
        Customer customer = customerRepository.findById(customerId).orElseThrow();

        if (customer == null)
            throw new CustomerNotFoundException("Customer not found");

        savingAcc.setId(UUID.randomUUID().toString());
        savingAcc.setCreatedAt(new Date());
        savingAcc.setCustomer(customer);
        savingAcc.setInterestRate(interestRate);
        savingAcc.setBalance(initialBalance);

        SavingAccount savedAcc = bankAccountRepository.save(savingAcc);

        return savedAcc;
    }

    @Override
    public List<Customer> listCustomers() {
        return List.of();
    }


    @Override
    public BankAccount getBankAccount(String accountId) {
        return null;
    }


    @Override
    public void debit(String accountId, double amount, String description) {

    }


    @Override
    public void credit(String accountId, double amount, String description) {

    }


    @Override
    public void transfer(String fromAccId, String toAccId, double amount) {

    }
}

