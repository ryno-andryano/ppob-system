package com.nutech.controller;

import com.nutech.model.dto.BalanceResponse;
import com.nutech.model.dto.ResponseTemplate;
import com.nutech.model.dto.TopUpRequest;
import com.nutech.service.TransactionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/topup")
    public ResponseEntity<ResponseTemplate<BalanceResponse>> topup(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
            @RequestBody TopUpRequest request) {
        String token = auth.substring(7);
        BalanceResponse data = transactionService.topup(token, request);
        ResponseTemplate<BalanceResponse> response = new ResponseTemplate<>(0, "Top Up Balance berhasil", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
