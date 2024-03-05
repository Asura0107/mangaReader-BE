package mangaReaderBE.mangaReaderBE.Auth;

import mangaReaderBE.mangaReaderBE.User.LoginResponseDTO;
import mangaReaderBE.mangaReaderBE.User.User;
import mangaReaderBE.mangaReaderBE.User.UserDTO;
import mangaReaderBE.mangaReaderBE.User.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService authService;
	

	@PostMapping("/login")
	public LoginResponseDTO login(@RequestBody UserLoginDTO payload) {
		return new LoginResponseDTO(authService.GenerateToken(payload));
	}

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody UserDTO newUser) {
		return this.authService.saveUser(newUser);
	}
}
