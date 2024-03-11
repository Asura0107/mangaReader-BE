package mangaReaderBE.mangaReaderBE.Pannel;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import mangaReaderBE.mangaReaderBE.Chapter.Chapter;
import mangaReaderBE.mangaReaderBE.Chapter.ChapterDAO;
import mangaReaderBE.mangaReaderBE.User.User;
import mangaReaderBE.mangaReaderBE.User.UserDTO;
import mangaReaderBE.mangaReaderBE.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class PanelService {
    @Autowired
    private PanelDAO panelDAO;
    @Autowired
    private Cloudinary cloudinary;
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

    public Panel save(PanelDTO panelDTO, MultipartFile image) throws IOException {
        String url = (String) cloudinary.uploader().upload(image.getBytes(),
                ObjectUtils.emptyMap()).get("url");
        Panel panel= new Panel(panelDTO.pannelNumber(), url);
        return panelDAO.save(panel);
    }

    public void delete(long id) {
        Panel panel = this.findById(id);
        this.panelDAO.delete(panel);
    }
    public Panel findByIdAndUpdate(long id, PanelDTO panelDTO) {
        Panel found = this.findById(id);
        found.setPannelNumber(panelDTO.pannelNumber());
        found.setImageUrl(panelDTO.imageUrl());
        return panelDAO.save(found);
    }

}
