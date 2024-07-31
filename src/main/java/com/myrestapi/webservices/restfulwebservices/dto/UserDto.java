package com.myrestapi.webservices.restfulwebservices.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String about;
    private Date birthday;
    private String emailId;
    private String website;
    private List<PostDto> posts;
}
