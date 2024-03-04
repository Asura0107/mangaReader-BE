package mangaReaderBE.mangaReaderBE.Chapter;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mangaReaderBE.mangaReaderBE.Pannel.Pannel;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private int number;
    private int requiredPoints;
    private boolean unloacked;
    @OneToMany
    private List<Pannel> pannels;

    public Chapter(String title, int number, int requiredPoints, boolean unloacked, List<Pannel> pannels) {
        this.title = title;
        this.number = number;
        this.requiredPoints = requiredPoints;
        this.unloacked = unloacked;
        this.pannels = pannels;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", number=" + number +
                ", requiredPoints=" + requiredPoints +
                ", unloacked=" + unloacked +
                ", pannels=" + pannels +
                '}';
    }
}
