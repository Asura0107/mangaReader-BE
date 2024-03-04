package mangaReaderBE.mangaReaderBE.Manga;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mangaReaderBE.mangaReaderBE.Chapter.Chapter;
import mangaReaderBE.mangaReaderBE.Comments.Comments;
import mangaReaderBE.mangaReaderBE.Favorite.Favorite;

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
    @OneToMany
    private List<Chapter> chapters;
    private String genre;
    @OneToMany
    private List<Comments> comments;

    public Manga(String title, String description, List<Chapter> chapters, String genre, List<Comments> comments) {
        this.title = title;
        this.description = description;
        this.chapters = new ArrayList<>();
        this.genre = genre;
        this.comments = new ArrayList<>();
    }
    public void addChapter(Chapter chapter) {
        this.chapters.add(chapter);
    }

    public void addComments(Comments comment) {
        this.comments.add(comment);
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
