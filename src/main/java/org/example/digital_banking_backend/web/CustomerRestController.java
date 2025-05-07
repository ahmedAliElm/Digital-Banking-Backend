package org.example.digital_banking_backend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.digital_banking_backend.dtos.CustomerDTO;
import org.example.digital_banking_backend.entities.Customer;
import org.example.digital_banking_backend.services.BankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
//@RequestMapping("/customers")
@AllArgsConstructor
@Slf4j
public class CustomerRestController {

    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    public List<CustomerDTO> customers(){
        return bankAccountService.listCustomers();
    }
}

