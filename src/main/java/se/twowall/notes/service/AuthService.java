package se.twowall.notes.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import se.twowall.notes.dto.AuthResponse;
import se.twowall.notes.entity.User;
import se.twowall.notes.exception.UsernameAlreadyExistsException;
import se.twowall.notes.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder BCrypt = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResponse login(String username, String password) {

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {

            return new AuthResponse(100L, "Username not found", "No token");
        }

        if (!BCrypt.matches(password, user.get().getPassword())) {
            System.out.println("Wrong password:");
            return new AuthResponse(99L, "Wrong user", "no token");

        }
        System.out.println("Login successful:" + user.get().getUsername());
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(secretKey)
                .compact();
        return new AuthResponse(200L, "testusername", "testtoken");

    }

}
