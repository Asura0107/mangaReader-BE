package mangaReaderBE.mangaReaderBE.Comments;

import mangaReaderBE.mangaReaderBE.Pannel.Panel;
import mangaReaderBE.mangaReaderBE.User.User;
import mangaReaderBE.mangaReaderBE.User.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;

    @GetMapping
    public List<Comments> getComment(@RequestParam String title) {
        return commentsService.getComments(title);
    }

    @PostMapping
    public Comments save(@RequestParam String title, @RequestBody CommentsDTO commentsDTO) {
        return this.commentsService.addComment(title, commentsDTO);
    }

    @PatchMapping("/my-comment")
    public Comments getMeAndPatc(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody CommentsDTO commentsDTO, @RequestParam long commentId) {
        return this.commentsService.findAndPatchMyComment(currentAuthenticatedUser.getId(), commentsDTO, commentId);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {
        this.commentsService.findAndDelete(id);
    }

    @DeleteMapping("/delete/my-comment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDeleteMyComment(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestParam String title, @RequestParam long id) {
        this.commentsService.findAndDeleteMyComment(currentAuthenticatedUser.getId(), title, id);
    }
}
