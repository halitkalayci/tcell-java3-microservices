package com.turkcell.authserver.services.concretes;

import com.turkcell.authserver.core.services.JwtService;
import com.turkcell.authserver.entities.User;
import com.turkcell.authserver.services.abstracts.AuthService;
import com.turkcell.authserver.services.abstracts.UserService;
import com.turkcell.authserver.services.dtos.requests.LoginRequest;
import com.turkcell.authserver.services.dtos.requests.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Override
    public void register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        // Hashing
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        // Hassas bilgiler veritabanına "PLAIN TEXT" olarak yazılmaz.
        userService.add(user);
    }

    @Override
    public String login(LoginRequest loginRequest) {
        // TODO: Handle Exception.
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        if(!authentication.isAuthenticated())
            throw new RuntimeException("E-posta ya da şifre yanlış");

        // TODO: Add extra claims.
        return jwtService.generateToken(loginRequest.getEmail(), null);
    }
}
