package mangaReaderBE.mangaReaderBE.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mangaReaderBE.mangaReaderBE.Chapter.Chapter;
import mangaReaderBE.mangaReaderBE.Favorite.Favorite;
import mangaReaderBE.mangaReaderBE.enums.UserType;
import mangaReaderBE.mangaReaderBE.exception.BadRequestException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@JsonIgnoreProperties({"password", "credentialsNonExpired", "accountNonExpired", "authorities", "accountNonLocked", "enabled"})
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private String avatar;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Favorite> favorites;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private int points;


    public User(String name, String surname, String username, String email, String password, String avatar) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.userType = UserType.UTENTE;
        this.favorites = new ArrayList<>();
        this.points = 100;
//        this.unlockedChapters=new HashSet<>();
    }

    public void addFavorite(Favorite favorite) {
        this.favorites.add(favorite);
    }

    public int addPoints(int point) {
        int tot = this.points + point;
        return tot;
    }

    public int minusPoints(int point) {
        int tot;
        if (this.points < point) {
            throw new BadRequestException("non hai abbastanza punti");
        } else {
            tot = this.points - point;
        }
        return tot;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.userType.name()));
    }

    @Override
    public String getUsername() {
        return this.username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }
}

