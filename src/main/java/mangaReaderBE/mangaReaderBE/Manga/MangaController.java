package mangaReaderBE.mangaReaderBE.Manga;

import mangaReaderBE.mangaReaderBE.Chapter.Chapter;
import mangaReaderBE.mangaReaderBE.Comments.Comments;
import mangaReaderBE.mangaReaderBE.Pannel.Panel;
import mangaReaderBE.mangaReaderBE.Pannel.PanelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manga")
public class MangaController {
    @Autowired
    private MangaService mangaService;

    @GetMapping("/all")
    public Page<Manga> getAllManga(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.mangaService.getAllManga(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Manga findById(@PathVariable long id) {
        return this.mangaService.findById(id);
    }

    @GetMapping("/likes")
    public long numberLikes(@RequestParam long id) {
        return this.mangaService.numberLikes(id);
    }

    @GetMapping("/genre")
    public Page<Manga> getAllMangaByGenre(@RequestParam String genre, @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.mangaService.getByGenre(genre, page, size, orderBy);
    }

    @GetMapping
    public Page<Manga> getAllMangaByPartial(@RequestParam String title, @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.mangaService.findByPartialTitle(title, page, size, orderBy);
    }

    @GetMapping("/chapters")
    public List<Chapter> getChapters(@RequestParam String title) {
        return mangaService.getChapters(title);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Manga save(@RequestBody MangaDTO mangaDTO) {
        return this.mangaService.save(mangaDTO);
    }

    @PostMapping("/add/chapter")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Manga addChapter(@RequestParam String title, @RequestParam long chapterId) {
        return this.mangaService.addChapter(title, chapterId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Manga findByIdAndUpdate(@PathVariable long id, @RequestBody MangaDTO mangaDTO) {
        return this.mangaService.findByIdAndUpdate(id, mangaDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {
        this.mangaService.delete(id);
    }
}
