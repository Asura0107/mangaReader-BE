package mangaReaderBE.mangaReaderBE.Carta;

import mangaReaderBE.mangaReaderBE.Chapter.Chapter;
import mangaReaderBE.mangaReaderBE.Chapter.ChapterDTO;
import mangaReaderBE.mangaReaderBE.Favorite.Favorite;
import mangaReaderBE.mangaReaderBE.Manga.Manga;
import mangaReaderBE.mangaReaderBE.User.User;
import mangaReaderBE.mangaReaderBE.User.UserDAO;
import mangaReaderBE.mangaReaderBE.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartaService {
    @Autowired
    private CartaDAO cartaDAO;
    @Autowired
    private UserDAO userDAO;

    public Page<Carta> getAllCarta(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return cartaDAO.findAll(pageable);
    }

    public Carta findById(UUID id) {
        return cartaDAO.findById(id).orElseThrow(() -> new NotFoundException("la carta con id: " + id + " non è stato trovato"));
    }

    public Carta save(UUID userId, CartaDTO cartaDTO) {
        User user = userDAO.findById(userId).orElseThrow(() -> new NotFoundException("user non trovao"));
        Carta carta = new Carta(cartaDTO.numeroCarta(), cartaDTO.scadenza(), cartaDTO.cvv(), user);
        return cartaDAO.save(carta);
    }

    public Page<Carta> getUserCard(UUID userId, int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        Page<Carta> cards = cartaDAO.findAllByUserId(userId, pageable);
        if (cards.isEmpty()) {
            throw new NotFoundException("la lista carte è vuota");
        }
        return cards;
    }

    public void delete(UUID cartaId) {
        Carta carta = this.findById(cartaId);
        cartaDAO.delete(carta);
    }
}
