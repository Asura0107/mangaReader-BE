package mangaReaderBE.mangaReaderBE.likes;

import mangaReaderBE.mangaReaderBE.Comments.Comments;
import mangaReaderBE.mangaReaderBE.Comments.CommentsDTO;
import mangaReaderBE.mangaReaderBE.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikesController {
    @Autowired
    private LikesService likesService;

    @PostMapping
    public Likes save(@RequestParam long mangaId, @RequestBody LikesDTO likesDTO) {
        return this.likesService.addLike(mangaId, likesDTO);
    }

    @DeleteMapping("/delete/my-like")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDeleteMyLike(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestParam String title, @RequestParam long id) {
        this.likesService.findAndDeleteMyLike(currentAuthenticatedUser.getId(), title, id);
    }
}
