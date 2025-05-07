package org.example.digital_banking_backend.mappers;

import org.example.digital_banking_backend.dtos.CustomerDTO;
import org.example.digital_banking_backend.entities.Customer;
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
}

