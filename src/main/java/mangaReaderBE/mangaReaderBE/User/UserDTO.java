package mangaReaderBE.mangaReaderBE.User;

public record UserDTO(
        String name,
        String surname,
        String username,
        String email,
        String password
) {
}
