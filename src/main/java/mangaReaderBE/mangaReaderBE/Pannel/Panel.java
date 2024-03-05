package mangaReaderBE.mangaReaderBE.Pannel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Panel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int pannelNumber;
    private String imageUrl;



    public Panel(int pannelNumber, String imageUrl) {
        this.pannelNumber = pannelNumber;
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Pannel{" +
                "id=" + id +
                ", pannelNumber=" + pannelNumber +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
