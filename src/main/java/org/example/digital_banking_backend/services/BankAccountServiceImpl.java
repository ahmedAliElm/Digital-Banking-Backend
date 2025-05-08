package org.example.digital_banking_backend.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.digital_banking_backend.dtos.CustomerDTO;
import org.example.digital_banking_backend.entities.*;
import org.example.digital_banking_backend.enums.OperationType;
import org.example.digital_banking_backend.exceptions.BalanceNotEnoughException;
import org.example.digital_banking_backend.exceptions.BankAccountNotFoundException;
import org.example.digital_banking_backend.exceptions.CustomerNotFoundException;
import org.example.digital_banking_backend.mappers.BankAccountMapperImpl;
import org.example.digital_banking_backend.repositories.AccountOperationRepository;
import org.example.digital_banking_backend.repositories.BankAccountRepository;
import org.example.digital_banking_backend.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {

    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;
    private BankAccountMapperImpl dtoMapper;


    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {

        log.info("Saving new customer");
        Customer customer = dtoMapper.fromCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);

        return dtoMapper.fromCustomer(savedCustomer);
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
    public List<CustomerDTO> listCustomers() {

        List<Customer> customers = customerRepository.findAll();

        List<CustomerDTO> customerDTOS = customers.stream().map(customer -> dtoMapper.fromCustomer(customer)).collect(Collectors.toList());
        
        return customerDTOS;
    }


    @Override
    public BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException {

        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));

        return bankAccount;
    }


    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotEnoughException {

        BankAccount bankAccount = getBankAccount(accountId);

        if (bankAccount.getBalance() < amount)
            throw new BalanceNotEnoughException("Not enough balance.");

        AccountOperation accountOperation = new AccountOperation();

        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);

        accountOperationRepository.save(accountOperation);

        bankAccount.setBalance(bankAccount.getBalance() - amount);

        bankAccountRepository.save(bankAccount);
    }


    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {

        BankAccount bankAccount = getBankAccount(accountId);

        AccountOperation accountOperation = new AccountOperation();

        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);

        accountOperationRepository.save(accountOperation);

        bankAccount.setBalance(bankAccount.getBalance() + amount);

        bankAccountRepository.save(bankAccount);
    }


    @Override
    public void transfer(String fromAccId, String toAccId, double amount) throws BalanceNotEnoughException, BankAccountNotFoundException {

        debit(fromAccId, amount, "Transfer from " + fromAccId);

        credit(toAccId, amount, "Transfer to " + toAccId);

    }


    @Override
    public List<BankAccount> bankAccountList() {
        return bankAccountRepository.findAll();
    }


    @Override
    public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found."));

        return dtoMapper.fromCustomer(customer);
    }


    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {

        log.info("Updating new customer");
        Customer customer = dtoMapper.fromCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);

        return dtoMapper.fromCustomer(savedCustomer);
    }


    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}

