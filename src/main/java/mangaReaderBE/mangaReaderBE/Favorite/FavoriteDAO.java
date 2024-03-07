package mangaReaderBE.mangaReaderBE.Favorite;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FavoriteDAO extends JpaRepository<Favorite, Long> {
    Page<Favorite> findAllByUserId(UUID userId, Pageable pageable);
}
