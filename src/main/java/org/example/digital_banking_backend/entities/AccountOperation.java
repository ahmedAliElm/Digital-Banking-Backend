package org.example.digital_banking_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.digital_banking_backend.enums.OperationType;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOperation {

    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private BankAccount bankAccount;

}

