package org.example.Services;

import org.example.Dto.LoginRequestDto;
import org.example.Dto.SignupRequestDto;
import org.example.Model.User;
import org.example.Repository.UserRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method to register a user (no security on registration)
    public User register(SignupRequestDto registration) {
        if (userRepository.findByUsername(registration.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User(registration.getUsername(), registration.getPassword(), registration.getRoles());
        userRepository.addUser(user);
        return user;
    }

    // Method to login a user (no security on login)
    public User login(LoginRequestDto loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return user;
    }

    // Secure this method to allow only ADMIN users to look up user details
    @Secured("ROLE_ADMIN")
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}







