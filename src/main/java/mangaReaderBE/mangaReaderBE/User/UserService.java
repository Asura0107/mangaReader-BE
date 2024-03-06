package mangaReaderBE.mangaReaderBE.User;

import mangaReaderBE.mangaReaderBE.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public User findByIdAndUpdate(UUID userId, User updateUser) {
        User found = this.findById(userId);
        found.setSurname(updateUser.getSurname());
        found.setName(updateUser.getName());
        found.setUsername(updateUser.getUsername());
        found.setEmail(updateUser.getEmail());
        found.setPassword(updateUser.getPassword());
        found.setAvatar(updateUser.getAvatar());
        return usersDAO.save(found);
    }

    public void delete(UUID userId) {
        User user = this.findById(userId);
        usersDAO.delete(user);
    }
}
