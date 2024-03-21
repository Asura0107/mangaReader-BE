package mangaReaderBE.mangaReaderBE.Comments;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mangaReaderBE.mangaReaderBE.User.User;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private String username;
    private String avatar;

    private String content;

    public Comments(User user, String content, String username, String avatar) {
        this.user = user;
        this.content = content;
        this.username=username;
        this.avatar=avatar;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", user=" + user +
                ", content='" + content + '\'' +
                '}';
    }
}
