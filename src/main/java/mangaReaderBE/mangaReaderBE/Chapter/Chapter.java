package mangaReaderBE.mangaReaderBE.Chapter;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mangaReaderBE.mangaReaderBE.Pannel.Pannel;

import java.util.ArrayList;
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

    public Chapter(String title, int number,  boolean unloacked) {
        this.title = title;
        this.number = number;
        this.requiredPoints = 10;
        this.unloacked = unloacked;
        this.pannels = new ArrayList<>();
    }
    public void addPannel(Pannel pannel) {
        this.pannels.add(pannel);
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
