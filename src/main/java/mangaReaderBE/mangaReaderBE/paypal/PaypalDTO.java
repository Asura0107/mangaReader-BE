package mangaReaderBE.mangaReaderBE.paypal;

public record PaypalDTO(
        double amount,
        String emailPaypal,
        String passwordPaypal
) {
}
