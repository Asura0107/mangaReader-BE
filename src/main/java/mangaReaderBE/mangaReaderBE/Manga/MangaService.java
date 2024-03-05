package mangaReaderBE.mangaReaderBE.Manga;

import mangaReaderBE.mangaReaderBE.Chapter.Chapter;
import mangaReaderBE.mangaReaderBE.Chapter.ChapterService;
import mangaReaderBE.mangaReaderBE.Comments.Comments;
import mangaReaderBE.mangaReaderBE.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public class MangaService {
    @Autowired
    private MangaDAO mangaDAO;
    @Autowired
    private ChapterService chapterService;

    public Manga findById(long id) {
        return mangaDAO.findById(id).orElseThrow(() -> new NotFoundException("il manga con id: " + id + " non è stato trovato"));
    }

    public Manga save(MangaDTO mangaDTO) {
        Manga manga = new Manga(mangaDTO.title(), mangaDTO.description(), mangaDTO.cover(), mangaDTO.genre());
        return mangaDAO.save(manga);
    }

    public Manga addChapter(String title, long chapterId) {
        Manga manga = mangaDAO.findByTitle(title);
        Chapter chapter = chapterService.findById(chapterId);
        manga.addChapter(chapter);
        return mangaDAO.save(manga);
    }

    public void delete(long id) {
        Manga manga = this.findById(id);
        mangaDAO.delete(manga);
    }
}
