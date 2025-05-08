package org.example.digital_banking_backend;

import org.example.digital_banking_backend.dtos.CustomerDTO;
import org.example.digital_banking_backend.entities.*;
import org.example.digital_banking_backend.enums.AccountStatus;
import org.example.digital_banking_backend.enums.OperationType;
import org.example.digital_banking_backend.exceptions.BalanceNotEnoughException;
import org.example.digital_banking_backend.exceptions.BankAccountNotFoundException;
import org.example.digital_banking_backend.exceptions.CustomerNotFoundException;
import org.example.digital_banking_backend.repositories.AccountOperationRepository;
import org.example.digital_banking_backend.repositories.BankAccountRepository;
import org.example.digital_banking_backend.repositories.CustomerRepository;
import org.example.digital_banking_backend.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingBackendApplication.class, args);
    }

    //@Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                        BankAccountRepository bankAccountRepository,
                        AccountOperationRepository accountOperationRepository) {
        return args -> {
            Stream.of("Ali", "Hassan", "Ahmad").forEach(name -> {

                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");

                customerRepository.save(customer);
            });

            customerRepository.findAll().forEach(customer -> {

                CurrentAccount currentAccount = new CurrentAccount();

                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random() * 90000);
                currentAccount.setCustomer(customer);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setOverDraft(9000);

                bankAccountRepository.save(currentAccount);


                SavingAccount savingAccount = new SavingAccount();

                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random() * 90000);
                savingAccount.setCustomer(customer);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setInterestRate(5.5);

                bankAccountRepository.save(savingAccount);
            });

            bankAccountRepository.findAll().forEach(bankAccount -> {

                for (int i = 0; i < 10; i++) {

                    AccountOperation accountOperation = new AccountOperation();

                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random() * 120000);
                    accountOperation.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                    accountOperation.setBankAccount(bankAccount);

                    accountOperationRepository.save(accountOperation);
                }
            });
        };
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService) {

        return args -> {

            Stream.of("Hassan", "Ali", "Ayoub").forEach(name -> {

                CustomerDTO customer = new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");

                bankAccountService.saveCustomer(customer);
            });

            bankAccountService.listCustomers().forEach(customer -> {

                try {
                    bankAccountService.saveCurrentBankAccount(Math.random() * 90000, customer.getId(), 9000);

                    bankAccountService.saveSavingBankAccount(Math.random() * 120000, customer.getId(), 6.2);

                    List<BankAccount> bankAccounts =  bankAccountService.bankAccountList();

                    for (BankAccount bankAccount : bankAccounts) {

                        for (int i = 0; i < 10; i++) {

                            bankAccountService.credit(bankAccount.getId(), 10000 + Math.random() * 120000, "Credit");

                            bankAccountService.debit(bankAccount.getId(), 1000 + Math.random() * 1200, "Debit");
                        }
                    }
                }

                catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }

                catch (BalanceNotEnoughException | BankAccountNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        };
    }
}

