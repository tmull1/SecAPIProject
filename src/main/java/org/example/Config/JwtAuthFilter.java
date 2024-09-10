package org.example.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserAuthProvider userAuthProvider;

    public JwtAuthFilter(UserAuthProvider userAuthProvider) {
        this.userAuthProvider = userAuthProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Get the request path
        String path = request.getServletPath();

        // Bypass JWT validation for login and register endpoints
        if (path.equals("/api/login") || path.equals("/api/register")) {
            filterChain.doFilter(request, response);  // Skip JWT validation
            return;
        }

        // Get authorization header and validate
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        // If header is valid, get the user identity and set it on the SecurityContext
        if (header != null && header.startsWith("Bearer ")) {
            try {
                String token = header.split(" ")[1];
                SecurityContextHolder.getContext().setAuthentication(
                        userAuthProvider.validateToken(token)
                );
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
                response.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
                return;
            }
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}
