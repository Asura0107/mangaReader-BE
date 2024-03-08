package mangaReaderBE.mangaReaderBE.paypal;

import mangaReaderBE.mangaReaderBE.Carta.Carta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PaypalDAO extends JpaRepository<Paypal, UUID> {
    Page<Paypal> findAllByUserId(UUID userId, Pageable pageable);

}
