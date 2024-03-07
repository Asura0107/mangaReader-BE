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

    public Comments addComment(String title, CommentsDTO commentsDTO) {
        Manga manga = mangaDAO.findByTitle(title);
        User user = userDAO.findById(commentsDTO.user()).orElseThrow(() -> new NotFoundException("user non trovato"));
        if (manga != null) {
            Comments comments = new Comments(user, commentsDTO.content());
            manga.addComments(comments);
            mangaDAO.save(manga);
            commentsDAO.save(comments);
            return comments;
        } else {
            throw new NotFoundException("Manga non trovato");
        }
    }

    public void findAndDelete(long id) {
        Comments comments = this.findById(id);
        this.commentsDAO.delete(comments);
    }

    public void findAndDeleteMyComment(UUID userId, String title, long id) {
        Comments comment = this.findById(id);
        if (!comment.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Non sei autorizzato a eliminare questo commento.");
        }
        Manga manga = mangaDAO.findByTitle(title);
        if (manga != null) {
            List<Comments> comments = manga.getComments();
            comments.remove(comment);
            mangaDAO.save(manga);
        }
        this.commentsDAO.delete(comment);
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
