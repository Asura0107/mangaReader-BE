package mangaReaderBE.mangaReaderBE.Favorite;

import mangaReaderBE.mangaReaderBE.Manga.Manga;
import mangaReaderBE.mangaReaderBE.Manga.MangaDTO;
import mangaReaderBE.mangaReaderBE.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @GetMapping
    public Page<Favorite> getAllFavorite(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.favoriteService.getFavorites(page, size, orderBy);
    }

    @GetMapping
    public Page<Favorite> getMyFavorite(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.favoriteService.getUserFavorites(currentAuthenticatedUser.getId(), page, size, orderBy);
    }
    @GetMapping("/{id}")
    public Favorite findById(@PathVariable long id) {
        return this.favoriteService.findById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Favorite save(@AuthenticationPrincipal User currentAuthenticatedUser,@RequestBody long mangaId){
        return this.favoriteService.save(currentAuthenticatedUser.getId(), mangaId);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {
        this.favoriteService.delete(id);
    }
}
