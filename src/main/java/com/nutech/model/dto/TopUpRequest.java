package com.nutech.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TopUpRequest {

    @JsonProperty("top_up_amount")
    private int topUpAmount;

}
