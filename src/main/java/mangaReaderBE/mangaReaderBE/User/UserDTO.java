package mangaReaderBE.mangaReaderBE.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserDTO(
        String name,

        String surname,
        @NotEmpty(message = "devi inserire il username")
        String username,
        @NotEmpty(message = "devi inserire l'email")
        @Email
        String email,
        @NotEmpty(message = "devi inserire la password")
        String password,
        String avatar
) {
}
