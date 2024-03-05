package mangaReaderBE.mangaReaderBE.Manga;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MangaDAO extends JpaRepository<Manga, Long> {
    Manga findByTitle(String title);
}
