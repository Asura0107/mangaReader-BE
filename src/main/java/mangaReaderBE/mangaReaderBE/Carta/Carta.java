package mangaReaderBE.mangaReaderBE.Carta;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mangaReaderBE.mangaReaderBE.User.User;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Carta {
    @Id
    @GeneratedValue
    private UUID id;
    private double amount;
    private String numeroCarta;
    private LocalDate scadenza;
    private String cvv;
    private LocalDate dataPagamento;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Carta(double amount, String numeroCarta, LocalDate scadenza, String cvv, User user) {
        this.amount = amount;
        this.numeroCarta = numeroCarta;
        this.scadenza = scadenza;
        this.cvv = cvv;
        this.user = user;
        this.dataPagamento = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Carta{" +
                "id=" + id +
                ", numeroCarta='" + numeroCarta + '\'' +
                ", scadenza='" + scadenza + '\'' +
                ", cvv='" + cvv + '\'' +
                ", user=" + user +
                '}';
    }
}
