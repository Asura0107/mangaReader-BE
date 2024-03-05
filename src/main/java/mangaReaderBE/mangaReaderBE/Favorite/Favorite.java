package mangaReaderBE.mangaReaderBE.Favorite;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mangaReaderBE.mangaReaderBE.Manga.Manga;
import mangaReaderBE.mangaReaderBE.User.User;
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "mangaId")
    private Manga manga;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Favorite(Manga manga, User user) {
        this.manga = manga;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", manga=" + manga +
                ", user=" + user +
                '}';
    }
}
