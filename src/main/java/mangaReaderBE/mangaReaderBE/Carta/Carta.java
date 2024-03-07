package mangaReaderBE.mangaReaderBE.Carta;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mangaReaderBE.mangaReaderBE.User.User;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Carta {
    @Id
    @GeneratedValue
    private UUID id;
    private String numeroCarta;
    private String scadenza;
    private String cvv;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Carta(String numeroCarta, String scadenza, String cvv, User user) {
        this.numeroCarta = numeroCarta;
        this.scadenza = scadenza;
        this.cvv = cvv;
        this.user = user;
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
