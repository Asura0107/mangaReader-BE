package mangaReaderBE.mangaReaderBE.Chapter;

import mangaReaderBE.mangaReaderBE.Manga.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterDAO extends JpaRepository<Chapter, Long> {
    Chapter findByNumber(int number);

}
