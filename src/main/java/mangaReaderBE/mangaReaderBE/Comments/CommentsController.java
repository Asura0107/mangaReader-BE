package mangaReaderBE.mangaReaderBE.Comments;

import mangaReaderBE.mangaReaderBE.Pannel.Panel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {
        this.commentsService.findAndDelete(id);
    }
}
