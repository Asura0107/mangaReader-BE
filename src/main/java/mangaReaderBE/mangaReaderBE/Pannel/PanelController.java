package mangaReaderBE.mangaReaderBE.Pannel;

import mangaReaderBE.mangaReaderBE.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/panel")
public class PanelController {
    @Autowired
    private PanelService panelService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Panel> getAllPanel(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.panelService.getPanels(page, size, orderBy);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Panel findById(@PathVariable long id) {
        return this.panelService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Panel save(@RequestBody PanelDTO panelDTO){
        return this.panelService.save(panelDTO);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {
        this.panelService.delete(id);
    }

}
