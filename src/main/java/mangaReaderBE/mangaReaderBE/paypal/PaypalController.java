package mangaReaderBE.mangaReaderBE.paypal;

import mangaReaderBE.mangaReaderBE.Carta.Carta;
import mangaReaderBE.mangaReaderBE.Carta.CartaDTO;
import mangaReaderBE.mangaReaderBE.Carta.CartaService;
import mangaReaderBE.mangaReaderBE.User.User;
import mangaReaderBE.mangaReaderBE.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/paypal")
public class PaypalController {
    @Autowired
    private PaypalService paypalService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Paypal> getAllCarta(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.paypalService.getAllPaypal(page, size, orderBy);
    }

    @GetMapping("/my-paypal")
    public Page<Paypal> getMyCard(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.paypalService.getUserPaypal(currentAuthenticatedUser.getId(), page, size, orderBy);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Paypal findById(@PathVariable UUID id) {
        return this.paypalService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Paypal save(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody @Validated PaypalDTO paypalDTO, BindingResult validation) {
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.paypalService.save(currentAuthenticatedUser.getId(), paypalDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        paypalService.delete(id);
    }


}
