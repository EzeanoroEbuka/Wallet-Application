package africa.semicolon.wollet.repositories;

import africa.semicolon.wollet.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
