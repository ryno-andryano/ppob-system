package com.nutech.controller;

import com.nutech.model.dto.*;
import com.nutech.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<ResponseTemplate<Object>> registration(@RequestBody RegistrationRequest request) {
        if (userService.isExist(request.getEmail())) {
            ResponseTemplate<Object> response = new ResponseTemplate<>(102, "Email sudah terdaftar", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        userService.registration(request);
        ResponseTemplate<Object> response = new ResponseTemplate<>(0, "Registrasi berhasil silahkan login", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseTemplate<LoginResponse>> login(@RequestBody LoginRequest request) {
        if (!userService.isExist(request.getEmail())) {
            ResponseTemplate<LoginResponse> response = new ResponseTemplate<>(102, "Email tidak ditemukan", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        LoginResponse data = userService.login(request);
        ResponseTemplate<LoginResponse> response = new ResponseTemplate<>(0, "Login Sukses", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<ResponseTemplate<ProfileResponse>> getProfile(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        String token = auth.substring(7);
        ProfileResponse data = userService.getProfile(token);
        ResponseTemplate<ProfileResponse> response = new ResponseTemplate<>(0, "Sukses", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/profile/update")
    public ResponseEntity<ResponseTemplate<ProfileResponse>> updateProfile(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
            @RequestBody ProfileUpdateRequest request) {
        String token = auth.substring(7);
        ProfileResponse data = userService.updateProfile(token, request);
        ResponseTemplate<ProfileResponse> response = new ResponseTemplate<>(0, "Update Pofile berhasil", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/balance")
    public ResponseEntity<ResponseTemplate<BalanceResponse>> getBalance(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        String token = auth.substring(7);
        BalanceResponse data = userService.getBalance(token);
        ResponseTemplate<BalanceResponse> response = new ResponseTemplate<>(0, "Get Balance Berhasil", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
