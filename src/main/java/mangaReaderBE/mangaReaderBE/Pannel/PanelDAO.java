package mangaReaderBE.mangaReaderBE.Pannel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanelDAO extends JpaRepository<Panel, Long> {
}
