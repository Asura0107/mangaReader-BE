package mangaReaderBE.mangaReaderBE.paypal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PaypalDTO(
        @NotNull(message = "devi inserire l'amount")
        double amount,
        @NotEmpty(message = "devi inserire l'email paypal")
        @Email
        String emailPaypal,
        @NotEmpty(message = "devi inserire la password paypal")
        String passwordPaypal
) {
}
