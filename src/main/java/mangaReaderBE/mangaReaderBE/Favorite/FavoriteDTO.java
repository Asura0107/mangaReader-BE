package mangaReaderBE.mangaReaderBE.Favorite;

import java.util.UUID;

public record FavoriteDTO(
        long manga,
        UUID user
) {
}
