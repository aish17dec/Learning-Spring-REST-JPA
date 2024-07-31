package com.myrestapi.webservices.restfulwebservices.dto;

import lombok.Data;

@Data
public class JWTAuthenticationResponseDto {
    private String token;
    private String refreshToken;
}
