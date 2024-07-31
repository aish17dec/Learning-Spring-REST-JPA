package com.myrestapi.webservices.restfulwebservices.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PostDto {
    private String postId;
    private String userName;
    private String content;
    private Date createdAt;
    private Date updatedAt;
}
