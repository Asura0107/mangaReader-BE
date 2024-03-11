package mangaReaderBE.mangaReaderBE.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesDAO extends JpaRepository<Likes, Long> {
}
