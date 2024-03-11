package mangaReaderBE.mangaReaderBE.Manga;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mangaReaderBE.mangaReaderBE.Chapter.Chapter;
import mangaReaderBE.mangaReaderBE.Comments.Comments;
import mangaReaderBE.mangaReaderBE.Favorite.Favorite;
import mangaReaderBE.mangaReaderBE.likes.Likes;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String cover;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Chapter> chapters;
    private String genre;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comments> comments;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Likes> likes;

    public Manga(String title, String description, String cover, String genre) {
        this.title = title;
        this.description = description;
        this.cover = cover;
        this.chapters = new ArrayList<>();
        this.genre = genre;
        this.comments = new ArrayList<>();
        this.likes = new ArrayList<>();
    }

    public void addChapter(Chapter chapter) {
        this.chapters.add(chapter);
    }

    public void addComments(Comments comment) {
        if (!this.comments.contains(comment)) {
            this.comments.add(comment);
        }
    }

    public void removeComment(Comments comment) {
        for (Comments comments1 : this.comments) {
            if (comments1.getId() == comment.getId()) {
                comments.remove(comment);
            }
        }
    }

    public void addLike(Likes like) {
        if (!this.likes.contains(like)) {
            this.likes.add(like);
        }
    }

    public void removeLike(Likes like) {
        this.likes.remove(like);
    }

    @Override
    public String toString() {
        return "Manga{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", chapters=" + chapters +
                ", genre='" + genre + '\'' +
                ", comments=" + comments +
                '}';
    }
}
