package mangaReaderBE.mangaReaderBE.paypal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Paypal {
    @Id
    @GeneratedValue
    private UUID id;
    private double amount;
    private String emailPaypal;
}
