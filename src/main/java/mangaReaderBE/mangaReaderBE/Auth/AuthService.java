package mangaReaderBE.mangaReaderBE.Auth;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import mangaReaderBE.mangaReaderBE.Security.JWTTools;
import mangaReaderBE.mangaReaderBE.User.*;
import mangaReaderBE.mangaReaderBE.exception.BadRequestException;
import mangaReaderBE.mangaReaderBE.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@Service
public class AuthService {
    @Autowired
    private UserService usersService;
    @Autowired
    private Cloudinary cloudinary;

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
                payload.email(), bcrypt.encode(payload.password()), payload.avatar());
        return usersDAO.save(newUser);
    }

    public User findAndPostAvatar(UUID id, MultipartFile image) throws IOException {
        User user = usersService.findById(id);
        String url = (String) cloudinary.uploader().upload(image.getBytes(),
                ObjectUtils.emptyMap()).get("url");
        user.setAvatar(url);
        return usersDAO.save(user);
    }


}
