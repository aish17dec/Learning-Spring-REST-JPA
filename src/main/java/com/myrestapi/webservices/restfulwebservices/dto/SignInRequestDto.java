package com.myrestapi.webservices.restfulwebservices.dto;

import lombok.Data;

@Data
public class SignInRequestDto {
    private String userName;
    private String password;
}
