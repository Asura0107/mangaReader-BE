package mangaReaderBE.mangaReaderBE.Chapter;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mangaReaderBE.mangaReaderBE.Pannel.Panel;
import mangaReaderBE.mangaReaderBE.User.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private LocalDate date;

    @ManyToMany
    @JoinTable(
            name = "user_unlocked_chapters",
            joinColumns = @JoinColumn(name = "chapter_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> unlockedByUsers = new HashSet<>();


    public Chapter(String title, int number, boolean unlocked) {
        this.title = title;
        this.number = number;
        this.requiredPoints = 10;
        this.unlocked = unlocked;
        this.panels = new ArrayList<>();
        this.date = LocalDate.now();
    }

    public void addPannel(Panel panel) {
        this.panels.add(panel);
    }

    public void unlockForUser(User user) {
        this.unlockedByUsers.add(user);
    }

    public boolean isUnlockedForUser(User user) {
        return this.unlockedByUsers.contains(user);
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
