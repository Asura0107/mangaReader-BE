package mangaReaderBE.mangaReaderBE.likes;

import mangaReaderBE.mangaReaderBE.Favorite.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LikesDAO extends JpaRepository<Likes, Long> {
    Likes findByUserIdAndId(UUID userId, Long id);

}
