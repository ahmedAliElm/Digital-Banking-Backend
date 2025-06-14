package org.example.digital_banking_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.digital_banking_backend.enums.AccountStatus;
import jakarta.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 10)

public abstract class BankAccount {

    @Id
    private String id;

    private double balance;
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.LAZY)
    private List<AccountOperation> accountOperations;
}

