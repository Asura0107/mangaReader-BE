package mangaReaderBE.mangaReaderBE.Pannel;

import mangaReaderBE.mangaReaderBE.Chapter.Chapter;
import mangaReaderBE.mangaReaderBE.Chapter.ChapterDAO;
import mangaReaderBE.mangaReaderBE.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanelService {
    @Autowired
    private PanelDAO panelDAO;
    @Autowired
    private ChapterDAO chapterDAO;

    public List<Panel> getPanels(int number) {
        Chapter chapter = chapterDAO.findByNumber(number);
        if (chapter != null) {
            return chapter.getPanels();
        }
        return null;
    }

    public Panel findById(long id) {
        return panelDAO.findById(id).orElseThrow(() -> new NotFoundException("il pannello con id: " + id + " non è stato trovato"));
    }

    public Panel save(PanelDTO panelDTO) {
        return new Panel(panelDTO.pannelNumber(), panelDTO.imageUrl());
    }

    public void delete(long id) {
        Panel panel = this.findById(id);
        this.panelDAO.delete(panel);
    }

}
