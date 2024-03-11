package mangaReaderBE.mangaReaderBE.Comments;

import mangaReaderBE.mangaReaderBE.Manga.Manga;
import mangaReaderBE.mangaReaderBE.Manga.MangaDAO;
import mangaReaderBE.mangaReaderBE.User.User;
import mangaReaderBE.mangaReaderBE.User.UserDAO;
import mangaReaderBE.mangaReaderBE.User.UserDTO;
import mangaReaderBE.mangaReaderBE.exception.NotFoundException;
import mangaReaderBE.mangaReaderBE.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentsService {
    @Autowired
    private CommentsDAO commentsDAO;
    @Autowired
    private MangaDAO mangaDAO;
    @Autowired
    private UserDAO userDAO;

    public List<Comments> getComments(String title) {
        Manga manga = mangaDAO.findByTitle(title);
        if (manga != null) {
            return manga.getComments();
        }
        return null;
    }

    public Comments findById(long id) {
        return commentsDAO.findById(id).orElseThrow(() -> new NotFoundException("il commento con id: " + id + " non è stato trovato"));
    }

    public Comments addComment(long id, CommentsDTO commentsDTO) {
        Manga manga = mangaDAO.findById(id).orElseThrow(() -> new NotFoundException("manga non trovato con id: " + id));
        User user = userDAO.findById(commentsDTO.user()).orElseThrow(() -> new NotFoundException("user non trovato"));
        Comments comments = new Comments(user, commentsDTO.content());
        commentsDAO.save(comments);
        manga.addComments(comments);
        mangaDAO.save(manga);
        return comments;

    }

    public void findAndDelete(long commentId, long mangaId) {
        Comments comments = this.findById(commentId);
        Manga manga = mangaDAO.findById(mangaId).orElseThrow(()->new NotFoundException("manga non trovato"));
        if (manga != null) {
            manga.removeComment(comments);
            mangaDAO.save(manga);
        }
        this.commentsDAO.delete(comments);
    }

    public void findAndDeleteMyComment(UUID userId, String title, long id) {
        Comments comment = this.findById(id);
        if (!comment.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Non sei autorizzato a eliminare questo commento.");
        }
        Manga manga = mangaDAO.findByTitle(title);
        if (manga != null) {
            manga.removeComment(comment);
            mangaDAO.save(manga);
        }
        commentsDAO.delete(comment);
    }

    public Comments findAndPatchMyComment(UUID userId, CommentsDTO commentsDTO, long id) {
        User found = userDAO.findById(userId).orElseThrow(() -> new NotFoundException("user non trovato"));
        Comments comments = this.findById(id);
        if (commentsDTO.content() != null) {
            comments.setContent(commentsDTO.content());
        }
        comments.setUser(found);
        return commentsDAO.save(comments);
    }


}
