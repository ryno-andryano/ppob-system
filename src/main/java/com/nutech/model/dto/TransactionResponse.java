package com.nutech.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponse {

    @JsonProperty("invoice_number")
    private String invoiceNumber;

    @JsonProperty("service_code")
    private String serviceCode;

    @JsonProperty("service_name")
    private String serviceName;

    @JsonProperty("transaction_type")
    private String transactionType;

    @JsonProperty("total_amount")
    private int totalAmount;

    @JsonProperty("created_on")
    private String createdOn;

}
