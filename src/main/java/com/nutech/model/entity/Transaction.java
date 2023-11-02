package com.nutech.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    enum TransactionType {
        TOPUP, TRANSACTION
    }

    private String invoiceNumber;
    private String userEmail;
    private String serviceCode;
    private TransactionType transactionType;
    private String description;
    private int totalAmount;
    private String createdOn;

}
