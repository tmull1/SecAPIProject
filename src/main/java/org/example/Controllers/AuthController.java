package org.example.Controllers;

import org.example.Config.UserAuthProvider;
import org.example.Dto.LoginRequestDto;
import org.example.Dto.SignupRequestDto;
import org.example.Model.User;
import org.example.Services.UserService;
import org.owasp.encoder.Encode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    public AuthController(UserService userService, UserAuthProvider userAuthProvider) {
        this.userService = userService;
        this.userAuthProvider = userAuthProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequestDto loginRequest) {
        System.out.println("Login request received for user: " + loginRequest.getUsername());

        // Sanitize the username input
        String sanitizedUsername = Encode.forHtml(loginRequest.getUsername());

        // Create a new sanitized login request
        LoginRequestDto sanitizedLoginRequest = new LoginRequestDto(sanitizedUsername, loginRequest.getPassword());

        // Proceed with login using the sanitized data
        User user = userService.login(sanitizedLoginRequest);
        user.setToken(userAuthProvider.createToken(user.getUsername()));
        return ResponseEntity.ok(user);
    }


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody SignupRequestDto signupRequest) {
        // Sanitize the username input
        String sanitizedUsername = Encode.forHtml(signupRequest.getUsername());

        // Create a new sanitized signup request
        SignupRequestDto sanitizedSignupRequest = new SignupRequestDto(sanitizedUsername, signupRequest.getPassword(), signupRequest.getRoles());

        // Proceed with registration using the sanitized data
        User user = userService.register(sanitizedSignupRequest);
        user.setToken(userAuthProvider.createToken(user.getUsername()));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/token")
    public ResponseEntity<String> generateToken(@RequestBody LoginRequestDto loginRequest) {
        // Sanitize the username input
        String sanitizedUsername = Encode.forHtml(loginRequest.getUsername());

        // Create a new sanitized login request
        LoginRequestDto sanitizedLoginRequest = new LoginRequestDto(sanitizedUsername, loginRequest.getPassword());

        // Generate and return a token if login is valid
        User user = userService.login(sanitizedLoginRequest);
        String token = userAuthProvider.createToken(user.getUsername());
        return ResponseEntity.ok(token);
    }
}





