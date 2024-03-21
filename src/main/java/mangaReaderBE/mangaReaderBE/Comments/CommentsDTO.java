package mangaReaderBE.mangaReaderBE.Comments;

import java.util.UUID;

public record CommentsDTO(
        UUID user,
        String content,
        String username,
        String avatar
) {
}
