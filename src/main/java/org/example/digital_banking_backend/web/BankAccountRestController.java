package org.example.digital_banking_backend.web;

import lombok.AllArgsConstructor;
import org.example.digital_banking_backend.services.BankAccountService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BankAccountRestController {

    private BankAccountService bankAccountService;


}

