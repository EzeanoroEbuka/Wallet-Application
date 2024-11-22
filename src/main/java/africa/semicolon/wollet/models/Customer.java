package africa.semicolon.wollet.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.CascadeType.PERSIST;

@Setter
@Getter
@Entity
public class Customer {
    @Id
    private Long id;
    @OneToOne(cascade = PERSIST)
    private Wallet wallet;
}
