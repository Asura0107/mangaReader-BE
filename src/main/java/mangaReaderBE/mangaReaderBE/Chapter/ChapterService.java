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
import java.util.Optional;
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

    public Chapter findAndPatchUnlocked(long id, ChapterDTO chapterDTO) {
        Chapter chapter = this.findById(id);
        if (!chapter.isUnlocked()) {
            chapter.setUnlocked(chapterDTO.unlocked());
        }
        return chapterDAO.save(chapter);
    }


//    public Chapter findBymangaAndNumber(long mangaId, int number){
//        Manga manga=mangaDAO.findById(mangaId).orElseThrow(()->new NotFoundException("manga non trovato"));
//
//    }

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

    public void delete(long id, long mangaId) {
        Manga manga = mangaDAO.findById(mangaId).orElseThrow(() -> new NotFoundException("manga con id no trovato: " + mangaId));
        Chapter chapter1 = manga.getChapters().stream().filter(t -> t.getId() == id).findFirst().get();
        manga.getChapters().remove(chapter1);
        mangaDAO.save(manga);
        this.chapterDAO.delete(chapter1);
    }
}
