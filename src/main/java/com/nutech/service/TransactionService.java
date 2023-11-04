package com.nutech.service;

import com.nutech.model.dto.BalanceResponse;
import com.nutech.model.dto.TopUpRequest;
import com.nutech.model.entity.Transaction;
import com.nutech.repository.TransactionRepository;
import com.nutech.security.JwtGenerator;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final JwtGenerator jwtGenerator;


    public TransactionService(TransactionRepository transactionRepository, UserService userService, JwtGenerator jwtGenerator) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
    }

    public BalanceResponse topup(String token, TopUpRequest request) {
        String invoiceNumber = UUID.randomUUID().toString();
        String email = jwtGenerator.getEmailFromJwt(token);
        String transactionType = "TOPUP";
        int totalAmount = request.getTopUpAmount();
        String createdOn = Instant.now().toString();

        Transaction transaction = Transaction.builder()
                .invoiceNumber(invoiceNumber)
                .userEmail(email)
                .transactionType(transactionType)
                .description("Top Up Balance")
                .totalAmount(totalAmount)
                .createdOn(createdOn)
                .build();
        transactionRepository.insert(transaction);

        return userService.updateBalance(token, transactionType, totalAmount);
    }
}
