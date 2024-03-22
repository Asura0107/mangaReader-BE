package mangaReaderBE.mangaReaderBE.User;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import mangaReaderBE.mangaReaderBE.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserDAO usersDAO;


    public Page<User> getUsers(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return usersDAO.findAll(pageable);
    }


    public User findById(UUID userId) {
        return usersDAO.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public User findByEmail(String email) {
        return usersDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Email " + email + " non trovata"));
    }

    public User findByIdAndUpdate(UUID userId, UserDTO updateUser) {
        User found = this.findById(userId);
        found.setSurname(updateUser.surname());
        found.setName(updateUser.name());
        found.setUsername(updateUser.username());
        found.setEmail(updateUser.email());
        found.setPassword(updateUser.password());
        found.setAvatar(updateUser.avatar());
        return usersDAO.save(found);
    }

    public User findAndPatch(UUID userId, UserDTO updateUser) {
        User found = this.findById(userId);
        //se il campo da sostituire non è nullo allora lo va ad aggioranare
        if (updateUser.name() != null) {
            found.setName(updateUser.name());
        }
        if (updateUser.surname() != null) {
            found.setSurname(updateUser.surname());
        }
        if (updateUser.username() != null) {
            found.setUsername(updateUser.username());
        }
        if (updateUser.email() != null) {
            found.setEmail(updateUser.email());
        }
        if (updateUser.password() != null) {
            found.setPassword(updateUser.password());
        }
        if (updateUser.avatar() != null) {
            found.setAvatar(updateUser.avatar());
        }
        if (updateUser.points() > 0) {
            found.setPoints(updateUser.points());
        }
        return usersDAO.save(found);
    }

    public User addPoints(UUID userId, int points) {
        User found = this.findById(userId);
        int newTot = found.addPoints(points);
        found.setPoints(newTot);
        return usersDAO.save(found);
    }

    public User minusPoints(UUID userId, int points) {
        User found = this.findById(userId);
        int newTot = found.minusPoints(points);
        found.setPoints(newTot);
        return usersDAO.save(found);
    }

    public void delete(UUID userId) {
        User user = this.findById(userId);
        usersDAO.delete(user);
    }

}
