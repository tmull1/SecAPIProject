package org.example.Controllers;

import org.example.Config.UserAuthProvider;
import org.example.Dto.LoginRequestDto;
import org.example.Dto.SignupRequestDto;
import org.example.Model.User;
import org.example.Services.UserService;
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
        User user = userService.login(loginRequest);
        user.setToken(userAuthProvider.createToken(user.getUsername()));
        return ResponseEntity.ok(user);
    }


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody SignupRequestDto signupRequest) {
        User user = userService.register(signupRequest);
        user.setToken(userAuthProvider.createToken(user.getUsername()));
        return ResponseEntity.ok(user);
    }
}




