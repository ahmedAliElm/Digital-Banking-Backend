package org.example.digital_banking_backend.repositories;

import org.example.digital_banking_backend.entities.AccountOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {

    List<AccountOperation> findByBankAccountId(String bankAccountId);

    Page<AccountOperation> findByBankAccountId(String bankAccountId, Pageable pageable);
}

