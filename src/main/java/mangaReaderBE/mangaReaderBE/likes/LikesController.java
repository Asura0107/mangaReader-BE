package mangaReaderBE.mangaReaderBE.likes;

import mangaReaderBE.mangaReaderBE.Comments.Comments;
import mangaReaderBE.mangaReaderBE.Comments.CommentsDTO;
import mangaReaderBE.mangaReaderBE.Favorite.Favorite;
import mangaReaderBE.mangaReaderBE.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/like")
public class LikesController {
    @Autowired
    private LikesService likesService;

    @PostMapping
    public Likes save(@RequestParam long mangaId, @RequestBody LikesDTO likesDTO) {
        return this.likesService.addLike(mangaId, likesDTO);
    }

    @DeleteMapping("/delete/{title}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDeleteMyLike(@RequestParam UUID userId, @PathVariable String title, @RequestParam long id) {
        this.likesService.findAndDeleteMyLike(userId, title, id);
    }

    @GetMapping("/user/{likeId}")
    public Likes getMyLike(@RequestParam UUID userId, @PathVariable long likeId) {
        return this.likesService.findByUser(userId, likeId);
    }
}
