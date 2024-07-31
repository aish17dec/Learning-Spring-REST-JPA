package com.myrestapi.webservices.restfulwebservices.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignInRequestDto {
    @NotNull
    @Size(min=4, max = 10)
    private String userName;

    @NotNull
    @Size(min=4, max = 20)
    private String password;
}
