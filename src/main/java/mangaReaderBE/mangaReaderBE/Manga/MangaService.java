package mangaReaderBE.mangaReaderBE.Manga;

import mangaReaderBE.mangaReaderBE.Chapter.Chapter;
import mangaReaderBE.mangaReaderBE.Chapter.ChapterService;
import mangaReaderBE.mangaReaderBE.Comments.Comments;
import mangaReaderBE.mangaReaderBE.Favorite.Favorite;
import mangaReaderBE.mangaReaderBE.User.User;
import mangaReaderBE.mangaReaderBE.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class MangaService {
    @Autowired
    private MangaDAO mangaDAO;
    @Autowired
    private ChapterService chapterService;

    public Page<Manga> getAllManga(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return mangaDAO.findAll(pageable);
    }
    public List<Chapter> getChapters(String title) {
        Manga manga = mangaDAO.findByTitle(title);
        if (manga != null) {
            return manga.getChapters();
        }
        return null;
    }

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

    public Page<Manga> getByGenre(String genre, int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        Page<Manga> mangas = mangaDAO.findByGenre(genre, pageable);
        if (genre.isEmpty()) {
            throw new NotFoundException("la lista manga di tipo: " + genre + " è vuota");
        }
        return mangas;
    }
    public Page<Manga> findByPartialTitle(String title,int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        Page<Manga> mangas = mangaDAO.findByPartialTitle(title, pageable);
        if (mangas.isEmpty()) {
            throw new NotFoundException("nessun manga trovato con questo titolo");
        }
        return mangas;
    }
}
