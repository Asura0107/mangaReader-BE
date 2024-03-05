package mangaReaderBE.mangaReaderBE.Pannel;

import mangaReaderBE.mangaReaderBE.Chapter.Chapter;
import mangaReaderBE.mangaReaderBE.Chapter.ChapterDAO;
import mangaReaderBE.mangaReaderBE.User.User;
import mangaReaderBE.mangaReaderBE.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanelService {
    @Autowired
    private PanelDAO panelDAO;
    @Autowired
    private ChapterDAO chapterDAO;

    public Page<Panel> getPanels(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return panelDAO.findAll(pageable);
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
