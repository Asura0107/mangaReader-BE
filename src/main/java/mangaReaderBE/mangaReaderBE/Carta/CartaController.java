package mangaReaderBE.mangaReaderBE.Carta;

import mangaReaderBE.mangaReaderBE.Chapter.Chapter;
import mangaReaderBE.mangaReaderBE.Manga.Manga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/carta")
public class CartaController {
    @Autowired
    private CartaService cartaService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Carta> getAllCarta(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.cartaService.getAllCrta(page, size, orderBy);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Carta findById(@PathVariable UUID id) {
        return this.cartaService.findById(id);
    }

}
