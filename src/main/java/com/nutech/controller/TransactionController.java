package com.nutech.controller;

import com.nutech.model.dto.*;
import com.nutech.service.ServiceService;
import com.nutech.service.TransactionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;
    private final ServiceService serviceService;

    public TransactionController(TransactionService transactionService, ServiceService serviceService) {
        this.transactionService = transactionService;
        this.serviceService = serviceService;
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

    @PostMapping("/transaction")
    public ResponseEntity<ResponseTemplate<TransactionResponse>> transaction(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
            @RequestBody TransactionRequest request) {
        String token = auth.substring(7);
        if (!serviceService.isExist(request.getServiceCode())) {
            ResponseTemplate<TransactionResponse> response = new ResponseTemplate<>(102, "Service atau Layanan tidak ditemukan", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        TransactionResponse data = transactionService.transaction(token, request);
        ResponseTemplate<TransactionResponse> response = new ResponseTemplate<>(0, "Transaksi berhasil", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/transaction/history")
    public ResponseEntity<ResponseTemplate<List<TransactionHistoryResponse>>> transactionHistory(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        String token = auth.substring(7);
        List<TransactionHistoryResponse> data = transactionService.transactionHistory(token);
        ResponseTemplate<List<TransactionHistoryResponse>> response = new ResponseTemplate<>(0, "Get History Berhasil", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
