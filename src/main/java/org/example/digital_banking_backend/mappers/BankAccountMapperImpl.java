package org.example.digital_banking_backend.mappers;

import org.example.digital_banking_backend.dtos.CurrentBankAccountDTO;
import org.example.digital_banking_backend.dtos.CustomerDTO;
import org.example.digital_banking_backend.dtos.SavingBankAccountDTO;
import org.example.digital_banking_backend.entities.CurrentAccount;
import org.example.digital_banking_backend.entities.Customer;
import org.example.digital_banking_backend.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {

    public CustomerDTO fromCustomer(Customer customer) {

        CustomerDTO customerDTO = new CustomerDTO();

        BeanUtils.copyProperties(customer, customerDTO);

        return customerDTO;
    }


    public Customer fromCustomer(CustomerDTO customerDTO) {

        Customer customer = new Customer();

        BeanUtils.copyProperties(customerDTO, customer);

        return customer;
    }


    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount) {

        SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();

        BeanUtils.copyProperties(savingAccount, savingBankAccountDTO);

        savingBankAccountDTO.setCustomer(fromCustomer(savingAccount.getCustomer()));

        return savingBankAccountDTO;
    }


    public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO) {

        SavingAccount savingAccount = new SavingAccount();

        BeanUtils.copyProperties(savingBankAccountDTO, savingAccount);

        savingAccount.setCustomer(fromCustomer(savingBankAccountDTO.getCustomer()));

        return savingAccount;
    }


    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount) {

        CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();

        BeanUtils.copyProperties(currentAccount, currentBankAccountDTO);

        currentBankAccountDTO.setCustomer(fromCustomer(currentAccount.getCustomer()));

        return currentBankAccountDTO;
    }


    public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO) {

        CurrentAccount currentAccount = new CurrentAccount();

        BeanUtils.copyProperties(currentBankAccountDTO, currentAccount);

        currentAccount.setCustomer(fromCustomer(currentBankAccountDTO.getCustomer()));

        return currentAccount;
    }
}

