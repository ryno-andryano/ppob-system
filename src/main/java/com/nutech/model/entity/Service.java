package com.nutech.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Service {

    private String serviceCode;
    private String serviceName;
    private String serviceIcon;
    private int serviceTariff;

}
