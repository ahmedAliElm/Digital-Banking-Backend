package org.example.digital_banking_backend.repositories;

import org.example.digital_banking_backend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}

