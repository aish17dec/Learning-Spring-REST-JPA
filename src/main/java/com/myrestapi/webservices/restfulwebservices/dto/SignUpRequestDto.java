package com.myrestapi.webservices.restfulwebservices.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SignUpRequestDto {
    private String userName;
    private String password;
    private String emailId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
}
