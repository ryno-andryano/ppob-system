package com.nutech.service;

import com.nutech.model.dto.LoginRequest;
import com.nutech.model.dto.LoginResponse;
import com.nutech.model.dto.RegistrationRequest;
import com.nutech.model.entity.User;
import com.nutech.repository.UserRepository;
import com.nutech.security.JwtGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;

    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    public boolean isExist(String email) {
        return userRepository.existByEmail(email);
    }

    public void registration(RegistrationRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.insert(user);
    }

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new LoginResponse(token);
    }
}
