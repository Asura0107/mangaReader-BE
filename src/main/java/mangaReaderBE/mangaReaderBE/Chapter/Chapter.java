package mangaReaderBE.mangaReaderBE.Chapter;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mangaReaderBE.mangaReaderBE.Pannel.Panel;

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
    private boolean unlocked;
    @OneToMany
    private List<Panel> panels;

    public Chapter(String title, int number,  boolean unlocked) {
        this.title = title;
        this.number = number;
        this.requiredPoints = 10;
        this.unlocked = unlocked;
        this.panels = new ArrayList<>();
    }
    public void addPannel(Panel panel) {
        this.panels.add(panel);
    }


    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", number=" + number +
                ", requiredPoints=" + requiredPoints +
                ", unloacked=" + unlocked +
                ", pannels=" + panels +
                '}';
    }
}
