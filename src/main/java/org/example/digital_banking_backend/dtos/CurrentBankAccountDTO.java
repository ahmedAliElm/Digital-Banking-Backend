package org.example.digital_banking_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.digital_banking_backend.enums.AccountStatus;
import java.util.Date;

@Data
public class CurrentBankAccountDTO extends BankAccountDTO {

    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customer;
    private double overdraft;
}

