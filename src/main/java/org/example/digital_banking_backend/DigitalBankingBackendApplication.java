package org.example.digital_banking_backend;

import org.example.digital_banking_backend.entities.*;
import org.example.digital_banking_backend.enums.AccountStatus;
import org.example.digital_banking_backend.enums.OperationType;
import org.example.digital_banking_backend.repositories.AccountOperationRepository;
import org.example.digital_banking_backend.repositories.BankAccountRepository;
import org.example.digital_banking_backend.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;
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
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository) {

        return args -> {

            BankAccount bankAccount1 = bankAccountRepository.findById("302c6da2-82fd-4a21-a9b5-5b2addd04d86").orElse(null);

            if (bankAccount1 != null) {

                System.out.println();

                System.out.println("Id : " + bankAccount1.getId());
                System.out.println("Balance : " + bankAccount1.getBalance());
                System.out.println("Customer : " + bankAccount1.getCustomer().getName());
                System.out.println("Date : " + bankAccount1.getCreatedAt());

                if (bankAccount1 instanceof SavingAccount)
                    System.out.println("Interest Rate : " + ((SavingAccount) bankAccount1).getInterestRate());

                else if (bankAccount1 instanceof CurrentAccount)
                    System.out.println("Overdraft : " + ((CurrentAccount) bankAccount1).getOverDraft());

                bankAccount1.getAccountOperations().forEach(op -> {
                    System.out.println(op.getType() + " \t " + op.getOperationDate() + " \t " + op.getAmount() + " \t " + op.getBankAccount());
                });
            }
        };
    }
}

