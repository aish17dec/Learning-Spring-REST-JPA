package com.myrestapi.webservices.restfulwebservices.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class SignUpRequestDto {
    @NotNull
    @Size(min=4, max = 10)
    private String userName;

    @NotNull
    @Size(min=4, max = 20)
    private String password;

    @NotNull
    private String emailId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
}
