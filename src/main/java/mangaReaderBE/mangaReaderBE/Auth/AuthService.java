package mangaReaderBE.mangaReaderBE.Auth;

import mangaReaderBE.mangaReaderBE.Security.JWTTools;
import mangaReaderBE.mangaReaderBE.User.*;
import mangaReaderBE.mangaReaderBE.exception.BadRequestException;
import mangaReaderBE.mangaReaderBE.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthService {
    @Autowired
    private UserService usersService;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private UserDAO usersDAO;
    @Autowired
    private JWTTools jwtTools;

    public String GenerateToken(UserLoginDTO payload) {
        User user = usersService.findByEmail(payload.email());
        if (bcrypt.matches(payload.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali sbagliate!");
        }
    }

    public User saveUser(UserDTO payload) {
        usersDAO.findByEmail(payload.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });

        User newUser = new User(payload.name(), payload.surname(), payload.username(),
                payload.email(), bcrypt.encode(payload.password()));
        return usersDAO.save(newUser);
    }


}
