package org.example.digital_banking_backend.repositories;

import org.example.digital_banking_backend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {



}

