package mangaReaderBE.mangaReaderBE.Carta;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CartaDTO(
        @NotNull(message = "devi inserire l'amount")
        double amount,
        @NotEmpty(message = "devi inserire il numero della carta")
        String numeroCarta,
        @NotNull(message = "devi inserire la scadenza della carta")
        @JsonFormat(pattern = "yyyy/MM/dd", shape = JsonFormat.Shape.STRING)
        LocalDate scadenza,
        @NotEmpty(message = "devi inserire il cvv della carta")
        @Size(min = 3, max = 3, message = "Il CVV deve essere composto da 3 cifre")
        String cvv
) {
}
