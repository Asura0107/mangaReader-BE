package mangaReaderBE.mangaReaderBE.Chapter;

import mangaReaderBE.mangaReaderBE.Manga.Manga;
import mangaReaderBE.mangaReaderBE.Manga.MangaDAO;
import mangaReaderBE.mangaReaderBE.Pannel.Panel;
import mangaReaderBE.mangaReaderBE.Pannel.PanelService;
import mangaReaderBE.mangaReaderBE.User.User;
import mangaReaderBE.mangaReaderBE.User.UserDAO;
import mangaReaderBE.mangaReaderBE.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChapterService {
    @Autowired
    private ChapterDAO chapterDAO;
    @Autowired
    private PanelService panelService;
    @Autowired
    private MangaDAO mangaDAO;
    @Autowired
    private UserDAO userDAO;


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
        Chapter chapter = new Chapter(chapterDTO.title(), chapterDTO.number(), chapterDTO.unlocked());
        return chapterDAO.save(chapter);
    }

    public Chapter findAndPatchUnlocked(long id,  UUID userId) {
        User user=userDAO.findById(userId).orElseThrow(()->new NotFoundException("user non trovato"));
        Chapter chapter = this.findById(id);
        if (!chapter.isUnlocked()) {
            chapter.setUnlocked(true);
            int tot= user.minusPoints(chapter.getRequiredPoints());
            user.setPoints(tot);
            userDAO.save(user);
        }
        return chapterDAO.save(chapter);
    }

    public Chapter findAndUpdate(long id, ChapterDTO chapterDTO) {
        Chapter chapter = this.findById(id);
        chapter.setTitle(chapterDTO.title());
        chapter.setNumber(chapterDTO.number());
        chapter.setUnlocked(chapterDTO.unlocked());
        return chapterDAO.save(chapter);
    }


    public Chapter findById(long id) {
        return chapterDAO.findById(id).orElseThrow(() -> new NotFoundException("il capitolo con id: " + id + " non è stato trovato"));
    }

    public void delete(long id) {
        Chapter chapter = this.findById(id);
        this.chapterDAO.delete(chapter);
    }
}
