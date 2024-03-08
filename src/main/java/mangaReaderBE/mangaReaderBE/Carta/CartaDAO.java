package mangaReaderBE.mangaReaderBE.Carta;

import mangaReaderBE.mangaReaderBE.Favorite.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartaDAO extends JpaRepository<Carta, UUID> {
    Page<Carta> findAllByUserId(UUID userId, Pageable pageable);

}
