package com.nutech.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    enum TransactionType {
        TOPUP, TRANSACTION
    }

    @JsonProperty("invoice_number")
    private String invoiceNumber;

    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("service_code")
    private String serviceCode;

    @JsonProperty("transaction_type")
    private TransactionType transactionType;

    private String description;

    @JsonProperty("total_amount")
    private int totalAmount;

    @JsonProperty("created_on")
    private String createdOn;

}
