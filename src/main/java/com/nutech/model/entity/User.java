package com.nutech.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String profileImage;
    private int balance;

}
