package org.example;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Component
public class UserAuthProvider {

    @Value("${security.jwt.secret}")
    private String SECRET_KEY;

    @Value("${security.jwt.expiration}")
    private long EXPIRATION;

    private final UserService userService;

    public UserAuthProvider(UserService userService) {
        this.userService = userService;
    }

    public String createToken(String username) {
        Date now = new Date();

        return JWT.create()
                .withIssuer(username)  // Use the username as the issuer
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + EXPIRATION))  // Token expiration
                .sign(Algorithm.HMAC256(SECRET_KEY));  // Sign with the secret key
    }

    public Authentication validateToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getIssuer();  // Extract the username from the token

        // Find the user by username
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("Invalid JWT token");
        }

        // Create an authentication object and return it
        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
}



