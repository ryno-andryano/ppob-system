package com.nutech.service;

import com.nutech.model.dto.*;
import com.nutech.model.entity.Service;
import com.nutech.model.entity.Transaction;
import com.nutech.repository.TransactionRepository;
import com.nutech.security.JwtGenerator;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@org.springframework.stereotype.Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final ServiceService serviceService;
    private final JwtGenerator jwtGenerator;


    public TransactionService(TransactionRepository transactionRepository, UserService userService, ServiceService serviceService, JwtGenerator jwtGenerator) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.serviceService = serviceService;
        this.jwtGenerator = jwtGenerator;
    }

    public BalanceResponse topup(String token, TopUpRequest request) {
        String email = jwtGenerator.getEmailFromJwt(token);

        Transaction transaction = Transaction.builder()
                .invoiceNumber(UUID.randomUUID().toString())
                .userEmail(email)
                .transactionType("TOPUP")
                .description("Top Up Balance")
                .totalAmount(request.getTopUpAmount())
                .createdOn(Instant.now().toString())
                .build();
        transactionRepository.insert(transaction);

        return userService.updateBalance(token, transaction.getTransactionType(), transaction.getTotalAmount());
    }

    public TransactionResponse transaction(String token, TransactionRequest request) {
        String email = jwtGenerator.getEmailFromJwt(token);
        Service service = serviceService.getService(request.getServiceCode());

        Transaction transaction = Transaction.builder()
                .invoiceNumber(UUID.randomUUID().toString())
                .userEmail(email)
                .transactionType("PAYMENT")
                .description(service.getServiceName())
                .totalAmount(service.getServiceTariff())
                .createdOn(Instant.now().toString())
                .build();
        transactionRepository.insert(transaction);

        userService.updateBalance(token, transaction.getTransactionType(), transaction.getTotalAmount());

        return TransactionResponse.builder()
                .invoiceNumber(transaction.getInvoiceNumber())
                .serviceCode(service.getServiceCode())
                .serviceName(service.getServiceName())
                .transactionType(transaction.getTransactionType())
                .totalAmount(transaction.getTotalAmount())
                .createdOn(transaction.getCreatedOn())
                .build();
    }

    public List<TransactionHistoryResponse> transactionHistory(String token) {
        String email = jwtGenerator.getEmailFromJwt(token);
        return transactionRepository.findAll(email);
    }
}
