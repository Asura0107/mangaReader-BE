package mangaReaderBE.mangaReaderBE.Comments;

import java.util.UUID;

public record CommentsDTO(
        UUID user,
        String profileUrl,
        String username,
        String content
) {
}
