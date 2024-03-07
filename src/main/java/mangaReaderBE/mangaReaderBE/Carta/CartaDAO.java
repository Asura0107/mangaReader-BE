package mangaReaderBE.mangaReaderBE.Carta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartaDAO extends JpaRepository<Carta, UUID> {
}
