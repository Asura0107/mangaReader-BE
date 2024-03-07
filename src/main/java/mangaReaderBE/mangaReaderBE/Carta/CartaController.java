package mangaReaderBE.mangaReaderBE.Carta;

import mangaReaderBE.mangaReaderBE.Chapter.Chapter;
import mangaReaderBE.mangaReaderBE.Chapter.ChapterDTO;
import mangaReaderBE.mangaReaderBE.Favorite.Favorite;
import mangaReaderBE.mangaReaderBE.Manga.Manga;
import mangaReaderBE.mangaReaderBE.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        return this.cartaService.getAllCarta(page, size, orderBy);
    }

    @GetMapping("/my-card")
    public Page<Carta> getMyCard(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.cartaService.getUserCard(currentAuthenticatedUser.getId(), page, size, orderBy);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Carta findById(@PathVariable UUID id) {
        return this.cartaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Carta save(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody CartaDTO cartaDTO) {
        return this.cartaService.save(currentAuthenticatedUser.getId(), cartaDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        cartaService.delete(id);
    }


}
