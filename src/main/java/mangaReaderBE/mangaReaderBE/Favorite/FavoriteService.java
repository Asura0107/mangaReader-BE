package mangaReaderBE.mangaReaderBE.Favorite;

import mangaReaderBE.mangaReaderBE.Manga.Manga;
import mangaReaderBE.mangaReaderBE.Manga.MangaDAO;
import mangaReaderBE.mangaReaderBE.User.User;
import mangaReaderBE.mangaReaderBE.User.UserDAO;
import mangaReaderBE.mangaReaderBE.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
