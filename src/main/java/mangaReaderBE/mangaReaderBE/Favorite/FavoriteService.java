package mangaReaderBE.mangaReaderBE.Favorite;

import mangaReaderBE.mangaReaderBE.Manga.Manga;
import mangaReaderBE.mangaReaderBE.Manga.MangaDAO;
import mangaReaderBE.mangaReaderBE.User.User;
import mangaReaderBE.mangaReaderBE.User.UserDAO;
import mangaReaderBE.mangaReaderBE.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteDAO favoriteDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private MangaDAO mangaDAO;
    public Page<Favorite> getFavorites(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return favoriteDAO.findAll(pageable);
    }

    public Page<Favorite> getUserFavorites(UUID userId, int pageNumber, int size, String orderBy) {
        User user=userDAO.findById(userId).orElseThrow(()->new NotFoundException(userId));
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        Page<Favorite> favorites=favoriteDAO.findAllByUserId(userId,pageable);
        if (favorites.isEmpty()){
            throw new NotFoundException("la lista preferiti è vuota");
        }
        return favorites;
    }

    public Favorite findById(long id) {
        return favoriteDAO.findById(id).orElseThrow(() -> new NotFoundException("il favorite con id: " + id + " non è stato trovato"));
    }

    public Favorite save(UUID userId, long mangaId) {
        Manga manga = mangaDAO.findById(mangaId).orElseThrow(() -> new NotFoundException("manga non trovato con id: " + mangaId));
        User user = userDAO.findById(userId).orElseThrow(() -> new NotFoundException("user non trovato con id: " + userId));
        Favorite favorite = new Favorite(manga, user);
        return favoriteDAO.save(favorite);
    }

    public void delete(long id) {
        Favorite favorite = this.findById(id);
        favoriteDAO.delete(favorite);
    }
}
