package mangaReaderBE.mangaReaderBE.likes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mangaReaderBE.mangaReaderBE.User.User;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Likes(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Likes{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}
