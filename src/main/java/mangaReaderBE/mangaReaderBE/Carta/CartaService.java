package mangaReaderBE.mangaReaderBE.Carta;

import mangaReaderBE.mangaReaderBE.Manga.Manga;
import mangaReaderBE.mangaReaderBE.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartaService {
    @Autowired
    private CartaDAO cartaDAO;
    public Page<Carta> getAllCrta(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return cartaDAO.findAll(pageable);
    }
    public Carta findById(UUID id) {
        return cartaDAO.findById(id).orElseThrow(() -> new NotFoundException("la carta con id: " + id + " non è stato trovato"));
    }
}
