package mangaReaderBE.mangaReaderBE.Favorite;

import mangaReaderBE.mangaReaderBE.Manga.Manga;
import mangaReaderBE.mangaReaderBE.Manga.MangaDTO;
import mangaReaderBE.mangaReaderBE.User.User;
import mangaReaderBE.mangaReaderBE.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Favorite> getAllFavorite(@RequestParam UUID userId, @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.favoriteService.getUserFavorites(userId, page, size, orderBy);
    }


    @GetMapping("/myfavorite")
    public List<Favorite> getMyFavorite(@RequestParam UUID userId) {
        return this.favoriteService.getFavoritesByUserId(userId);
    }

    @GetMapping("/manga/{mangaId}")
    public Favorite getMyFavorite(@RequestParam UUID userId, @PathVariable long mangaId) {
        return this.favoriteService.findByManga(userId, mangaId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Favorite findById(@PathVariable long id) {
        return this.favoriteService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Favorite save(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody FavoriteDTO favoriteDTO) {
        return this.favoriteService.save(currentAuthenticatedUser.getId(), favoriteDTO);
    }

    @DeleteMapping("/delete/{mangaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestParam UUID userId, @PathVariable long mangaId) {
        if (!currentAuthenticatedUser.getId().equals(userId)) {
            throw new UnauthorizedException("Non hai i permessi per eliminare questo preferito.");
        }
        this.favoriteService.deleteByUser(userId, mangaId);
    }
}
