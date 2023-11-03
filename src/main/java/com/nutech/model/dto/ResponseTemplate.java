package com.nutech.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseTemplate<T> {

    private int status;
    private String message;
    private T data;

}
