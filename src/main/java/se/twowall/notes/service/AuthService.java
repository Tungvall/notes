package se.twowall.notes.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.twowall.notes.dto.LoginResponse;
import se.twowall.notes.entity.User;
import se.twowall.notes.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder BCrypt = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse login(String username, String password) {
        String SECRET_KEY = "SupermegahemligsupernyckelSomArMinst32TeckenLAng!!!";

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        if (!BCrypt.matches(password, user.get().getPassword())) {
            System.out.println("Wrong password:");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invali ");

        }
//      https://www.baeldung.com/spring-security-sign-jwt-token
        String token = Jwts.builder()
                .setSubject(user.get().getUsername())
                .claim("role", user.get().getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        System.out.println("Login successful:" + user.get().getUsername());
        return new LoginResponse(token, "Bearer", user.get().getUsername(), user.get().getRole());

    }
}
