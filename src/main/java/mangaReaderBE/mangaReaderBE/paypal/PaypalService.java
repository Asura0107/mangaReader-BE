package mangaReaderBE.mangaReaderBE.paypal;

import mangaReaderBE.mangaReaderBE.Carta.Carta;
import mangaReaderBE.mangaReaderBE.Carta.CartaDTO;
import mangaReaderBE.mangaReaderBE.User.User;
import mangaReaderBE.mangaReaderBE.User.UserDAO;
import mangaReaderBE.mangaReaderBE.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaypalService {
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private PaypalDAO paypalDAO;
    @Autowired
    private UserDAO userDAO;

    public Page<Paypal> getAllPaypal(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return paypalDAO.findAll(pageable);
    }

    public Paypal findById(UUID id) {
        return paypalDAO.findById(id).orElseThrow(() -> new NotFoundException("la carta con id: " + id + " non è stato trovato"));
    }

    public Paypal save(UUID userId, PaypalDTO paypalDTO) {
        User user = userDAO.findById(userId).orElseThrow(() -> new NotFoundException("user non trovao"));
        Paypal paypal = new Paypal(paypalDTO.amount(), paypalDTO.emailPaypal(), bcrypt.encode(paypalDTO.passwordPaypal()), user);
        return paypalDAO.save(paypal);
    }

    public Page<Paypal> getUserPaypal(UUID userId, int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        Page<Paypal> paypals = paypalDAO.findAllByUserId(userId, pageable);
        if (paypals.isEmpty()) {
            throw new NotFoundException("la lista carte è vuota");
        }
        return paypals;
    }

    public void delete(UUID paypalId) {
        Paypal paypal = this.findById(paypalId);
        paypalDAO.delete(paypal);
    }
}
