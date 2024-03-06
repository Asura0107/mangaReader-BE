package mangaReaderBE.mangaReaderBE.Chapter;

import mangaReaderBE.mangaReaderBE.Pannel.Panel;
import mangaReaderBE.mangaReaderBE.Pannel.PanelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Chapter addPanel(@RequestParam long chapterId, @RequestParam long panelId) {
        return this.chapterService.addPanel(chapterId, panelId);
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Chapter save(@RequestBody ChapterDTO chapterDTO){
        return this.chapterService.save(chapterDTO);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {
        this.chapterService.delete(id);
    }

}
