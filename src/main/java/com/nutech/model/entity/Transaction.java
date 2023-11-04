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

    @JsonProperty("invoice_number")
    private String invoiceNumber;

    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("transaction_type")
    private String transactionType;

    private String description;

    @JsonProperty("total_amount")
    private int totalAmount;

    @JsonProperty("created_on")
    private String createdOn;

}
