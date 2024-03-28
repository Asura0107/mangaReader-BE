package mangaReaderBE.mangaReaderBE.likes;

import mangaReaderBE.mangaReaderBE.Comments.Comments;
import mangaReaderBE.mangaReaderBE.Comments.CommentsDTO;
import mangaReaderBE.mangaReaderBE.Favorite.Favorite;
import mangaReaderBE.mangaReaderBE.Manga.Manga;
import mangaReaderBE.mangaReaderBE.Manga.MangaDAO;
import mangaReaderBE.mangaReaderBE.User.User;
import mangaReaderBE.mangaReaderBE.User.UserDAO;
import mangaReaderBE.mangaReaderBE.exception.BadRequestException;
import mangaReaderBE.mangaReaderBE.exception.NotFoundException;
import mangaReaderBE.mangaReaderBE.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LikesService {
    @Autowired
    private LikesDAO likesDAO;
    @Autowired
    private MangaDAO mangaDAO;
    @Autowired
    private UserDAO userDAO;

    public Likes findById(long id) {
        return likesDAO.findById(id).orElseThrow(() -> new NotFoundException("il like con id: " + id + " non è stato trovato"));
    }

    public Likes addLike(long mangaId, LikesDTO likesDTO) {
        Manga manga = mangaDAO.findById(mangaId).orElseThrow(() -> new NotFoundException("manga non trovato"));
        User user = userDAO.findById(likesDTO.user()).orElseThrow(() -> new NotFoundException("user non trovato"));
        boolean userAlreadyLiked = manga.getLikes().stream().anyMatch(like -> like.getUser().equals(user));
        if (userAlreadyLiked) {
            throw new BadRequestException("L'utente ha già messo un like su questo manga.");
        }
        Likes like = new Likes(user);
        likesDAO.save(like);
        manga.addLike(like);
        mangaDAO.save(manga);
        return like;

    }

    public void findAndDelete(long id, String title) {
        Likes likes = this.findById(id);
        Manga manga = mangaDAO.findByTitle(title);
        if (manga != null) {
            manga.removeLike(likes);
            mangaDAO.save(manga);
        }
        this.likesDAO.delete(likes);
    }

    public void findAndDeleteMyLike(UUID userId, String title, long id) {
        Likes likes = this.findById(id);
        if (!likes.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Non sei autorizzato a eliminare questo like.");
        }
        Manga manga = mangaDAO.findByTitle(title);
        if (manga != null) {
            manga.removeLike(likes);
            mangaDAO.save(manga);
        }
        this.likesDAO.delete(likes);
    }

    public Likes findByUser(UUID userId, long id) {
        Likes like = likesDAO.findByUserIdAndId(userId, id);
        if (like == null) {
            throw new NotFoundException("favorite non trovato");
        }
        return like;
    }
}
