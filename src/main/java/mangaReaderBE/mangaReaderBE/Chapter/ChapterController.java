package mangaReaderBE.mangaReaderBE.Chapter;

import mangaReaderBE.mangaReaderBE.Comments.Comments;
import mangaReaderBE.mangaReaderBE.Comments.CommentsDTO;
import mangaReaderBE.mangaReaderBE.Manga.Manga;
import mangaReaderBE.mangaReaderBE.Pannel.Panel;
import mangaReaderBE.mangaReaderBE.Pannel.PanelDTO;
import mangaReaderBE.mangaReaderBE.User.User;
import mangaReaderBE.mangaReaderBE.User.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @GetMapping("/get/panels")
    public List<Panel> getPanelChapter(@RequestParam long chapterId) {
        return chapterService.getPanels(chapterId);
    }

    @GetMapping("/{id}")
    public Chapter findById(@PathVariable long id) {
        return this.chapterService.findById(id);
    }

    @PostMapping("/{chapterId}/add/panels")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Chapter addPanel(@PathVariable long chapterId, @RequestParam long panelId) {
        return this.chapterService.addPanel(chapterId, panelId);
    }

    @PatchMapping("/unlocked")
    public Chapter PatchUnlocked(@RequestParam long chapterId, @AuthenticationPrincipal User currentAuthenticatedUser) {
        return this.chapterService.findAndPatchUnlocked(chapterId, currentAuthenticatedUser.getId());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Chapter findByIdAndUpdate(@PathVariable long id, @RequestBody ChapterDTO chapterDTO) {

        return this.chapterService.findAndUpdate(id, chapterDTO);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Chapter save(@RequestBody ChapterDTO chapterDTO) {
        return this.chapterService.save(chapterDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id, @RequestParam long mangaId) {
        this.chapterService.delete(id, mangaId);
    }

}
