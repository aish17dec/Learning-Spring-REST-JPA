package com.myrestapi.webservices.restfulwebservices.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateUserDto {
    private String name;
    private String about;
    private Date birthday;
    private String emailId;
    private String website;
}
