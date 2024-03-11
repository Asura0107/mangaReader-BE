package mangaReaderBE.mangaReaderBE.likes;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record LikesDTO(
        @NotEmpty(message = "è necessario l'id dello user")
        UUID user
) {
}
