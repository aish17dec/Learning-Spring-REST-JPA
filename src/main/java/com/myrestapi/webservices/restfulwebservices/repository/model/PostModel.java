package com.myrestapi.webservices.restfulwebservices.repository.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;


@Entity
@Table(name = "post")
@Getter
@Setter
public class PostModel extends BaseModel{
    @Column(name="post_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserModel.class)
    @JoinColumn(name="user_id", nullable=false)
    private UserModel user;
}
