package mangaReaderBE.mangaReaderBE.Chapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterDAO extends JpaRepository<Chapter, Long> {
    Chapter findByNumber(int number);
}
