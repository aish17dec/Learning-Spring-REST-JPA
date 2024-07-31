package com.myrestapi.webservices.restfulwebservices.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto {
    private String name;
    private String userName;
    private String about;
    private LocalDate birthday;
    private String emailId;
    private String website;
    private List<PostDto> posts;
}
