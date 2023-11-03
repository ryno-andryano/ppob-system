package com.nutech.controller;

import com.nutech.model.dto.LoginRequest;
import com.nutech.model.dto.LoginResponse;
import com.nutech.model.dto.RegistrationRequest;
import com.nutech.model.dto.ResponseTemplate;
import com.nutech.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
