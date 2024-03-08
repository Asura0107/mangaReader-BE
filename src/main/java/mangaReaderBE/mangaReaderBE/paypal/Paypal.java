package mangaReaderBE.mangaReaderBE.paypal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"passwordPaypal"})
public class Paypal {
    @Id
    @GeneratedValue
    private UUID id;
    private double amount;
    private String emailPaypal;
    private String passwordPaypal;
    private LocalDate dataPagamento;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Paypal(double amount, String emailPaypal, String passwordPaypal, User user) {
        this.amount = amount;
        this.emailPaypal = emailPaypal;
        this.passwordPaypal = passwordPaypal;
        this.dataPagamento = LocalDate.now();
        this.user = user;
    }

    @Override
    public String toString() {
        return "Paypal{" +
                "id=" + id +
                ", amount=" + amount +
                ", emailPaypal='" + emailPaypal + '\'' +
                ", passwordPaypal='" + passwordPaypal + '\'' +
                ", dataPagamento=" + dataPagamento +
                ", user=" + user +
                '}';
    }
}
