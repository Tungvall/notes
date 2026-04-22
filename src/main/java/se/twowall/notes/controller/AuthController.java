package se.twowall.notes.controller;

import se.twowall.notes.dto.AuthResponse;
import se.twowall.notes.dto.LoginRequest;
import se.twowall.notes.dto.RegisterRequest;
import se.twowall.notes.dto.UserResponse;
import se.twowall.notes.service.AuthService;
import se.twowall.notes.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        System.out.println("Register request: " + request);
        return userService.registerUser(request.getUsername(), request.getPassword());
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        System.out.println("Login request: " + request);
        return authService.login(request.getUsername(), request.getPassword());
    }
}
