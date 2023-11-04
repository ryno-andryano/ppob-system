package com.nutech.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionRequest {

    @JsonProperty("service_code")
    private String serviceCode;

}
