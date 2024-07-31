package com.myrestapi.webservices.restfulwebservices.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PostDto {
    private String postId;
    private String content;
    @JsonProperty(value = "createdDate")
    private Date createdAt;
    @JsonProperty(value = "updatedDate")
    private Date updatedAt;
}
