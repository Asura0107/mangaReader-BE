package mangaReaderBE.mangaReaderBE.Chapter;

import mangaReaderBE.mangaReaderBE.Manga.Manga;
import mangaReaderBE.mangaReaderBE.Manga.MangaDAO;
import mangaReaderBE.mangaReaderBE.Pannel.Panel;
import mangaReaderBE.mangaReaderBE.Pannel.PanelService;
import mangaReaderBE.mangaReaderBE.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {
    @Autowired
    private ChapterDAO chapterDAO;
    @Autowired
    private PanelService panelService;
    @Autowired
    private MangaDAO mangaDAO;


    public List<Panel> getPanels(long id) {
        Chapter chapter = this.findById(id);
        return chapter.getPanels();
    }

    public Chapter addPanel(long chapterId, long pannelId) {
        Chapter chapter = chapterDAO.findById(chapterId).orElseThrow(() -> new NotFoundException("capitolo non trovato"));
        Panel panel = panelService.findById(pannelId);
        chapter.addPannel(panel);
        return chapterDAO.save(chapter);
    }

    public Chapter save(ChapterDTO chapterDTO) {
        Chapter chapter = new Chapter(chapterDTO.title(), chapterDTO.number(), chapterDTO.unloacked());
        return chapter;
    }

    public Chapter findById(long id) {
        return chapterDAO.findById(id).orElseThrow(() -> new NotFoundException("il capitolo con id: " + id + " non è stato trovato"));
    }

    public void delete(long id) {
        Chapter chapter = this.findById(id);
        this.chapterDAO.delete(chapter);
    }
}
