package com.example.elearningplatform.service;

import com.example.elearningplatform.dto.AuthenticationRequest;
import com.example.elearningplatform.dto.AuthenticationResponse;
import com.example.elearningplatform.dto.RegisterRequest;
import com.example.elearningplatform.entity.User;
import com.example.elearningplatform.options.Role;
import com.example.elearningplatform.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final HttpServletRequest requestHttp;
    public AuthenticationResponse register(RegisterRequest request) {

        User user= User.builder().
                firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole())).build();

        repository.save(user);

        return AuthenticationResponse.builder().token(jwtService.generateToken(user)).build();

    }

    public AuthenticationResponse login(AuthenticationRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new NoSuchElementException("User not found"));
        String token = jwtService.generateToken(user);

        HttpSession session = requestHttp.getSession(true);
        session.setAttribute("jwt", token);

        return AuthenticationResponse.builder().token(jwtService.generateToken(user)).build();
    }

    public void logout() {

        HttpSession session = requestHttp.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
    public  User getUserFromSession() {
        HttpSession session = requestHttp.getSession(false);
        if (session != null) {
            return (User) session.getAttribute("user");
        }
        return null;
    }
}
