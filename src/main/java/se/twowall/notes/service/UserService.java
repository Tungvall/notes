package se.twowall.notes.service;

import se.twowall.notes.dto.UserResponse;
import se.twowall.notes.entity.User;
import se.twowall.notes.exception.UsernameAlreadyExistsException;
import se.twowall.notes.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse registerUser(String username, String password) {

        if (userRepository.findByUsername(username).isPresent()) {
            System.out.println("User already exists");
            throw new UsernameAlreadyExistsException("User already exists");
        }

        String hashedPassword = bCrypt.encode(password);

        User user = new User(username, hashedPassword);
        User savedUser = userRepository.save(user);

        System.out.println("Registered user: " + savedUser.getUsername());
        return new UserResponse((savedUser.getId()), savedUser.getUsername());
    }


}
