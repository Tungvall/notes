package se.twowall.notes.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import se.twowall.notes.dto.*;
import se.twowall.notes.service.AuthService;
import se.twowall.notes.service.UserService;
import se.twowall.notes.service.NoteService;


@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService, NoteService noteService) {
        this.userService = userService;
        this.authService = authService;
    }

    //  https://www.geeksforgeeks.org/springboot/spring-boot-3-0-jwt-authentication-with-spring-security-using-mysql-database/
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request.getUsername(), request.getPassword());
    }
    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        return userService.registerUser(request.getUsername(), request.getPassword());
    }


    @PatchMapping("/password/")
    public boolean changePassword(@Valid @RequestBody PasswordChangeRequest request) {
        return userService.changePassword(request);
    }
}
