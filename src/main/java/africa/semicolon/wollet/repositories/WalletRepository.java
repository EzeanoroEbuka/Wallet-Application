package africa.semicolon.wollet.repositories;


import africa.semicolon.wollet.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
