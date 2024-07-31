package com.myrestapi.webservices.restfulwebservices.dto;

import lombok.Data;

@Data
public class UserCredentialsDto {
    private String userName;
    private String password;
    private String emailId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String country;
}
