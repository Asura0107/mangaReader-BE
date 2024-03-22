package mangaReaderBE.mangaReaderBE.User;

import mangaReaderBE.mangaReaderBE.Auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService usersService;
    @Autowired
    private AuthService authService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.usersService.getUsers(page, size, orderBy);
    }

    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        return currentAuthenticatedUser;
    }

    @PatchMapping("/me")
    public User getMeAndPatch(@RequestParam UUID userId, @RequestBody UserDTO updatedUser) {
        return this.usersService.findAndPatch(userId, updatedUser);
    }

    @PutMapping("/me")
    public User getMeAndUpdate(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody UserDTO updatedUser) {
        return this.usersService.findByIdAndUpdate(currentAuthenticatedUser.getId(), updatedUser);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getMeAndDelete(@AuthenticationPrincipal User currentAuthenticatedUser) {
        this.usersService.delete(currentAuthenticatedUser.getId());
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findById(@PathVariable UUID id) {
        return this.usersService.findById(id);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findByIdAndUpdate(@PathVariable UUID id, @RequestBody UserDTO updatedUser) {

        return this.usersService.findByIdAndUpdate(id, updatedUser);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findByIdAndPatch(@PathVariable UUID id, @RequestBody UserDTO updatedUser) {

        return this.usersService.findAndPatch(id, updatedUser);
    }

    @PatchMapping("/add-points")
    public User findByIdAndAddPoints(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestParam int points) {
        return this.usersService.addPoints(currentAuthenticatedUser.getId(), points);
    }

    @PatchMapping("/minus-points")
    public User findByIdAndReducePoints(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestParam int points) {
        return this.usersService.minusPoints(currentAuthenticatedUser.getId(), points);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id) {
        this.usersService.delete(id);
    }

    @PostMapping("/avatar")
    public User uploadCover(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestParam("avatar") MultipartFile image) throws IOException {
        return this.authService.findAndPostAvatar(currentAuthenticatedUser.getId(), image);
    }
}
