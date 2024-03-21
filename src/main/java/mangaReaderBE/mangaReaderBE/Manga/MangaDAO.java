package mangaReaderBE.mangaReaderBE.Manga;

import mangaReaderBE.mangaReaderBE.Favorite.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MangaDAO extends JpaRepository<Manga, Long> {
    Manga findByTitle(String title);

    Page<Manga> findByGenre(String genre, Pageable pageable);

    @Query("SELECT m FROM Manga m WHERE LOWER(m.title) LIKE %:title%")
    Page<Manga> findByPartialTitle(String title, Pageable pageable);

    Manga findByChaptersId(Long chapterId);
}
