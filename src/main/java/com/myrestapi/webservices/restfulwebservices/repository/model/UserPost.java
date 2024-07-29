package com.myrestapi.webservices.restfulwebservices.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myrestapi.webservices.restfulwebservices.repository.model.User;

import javax.persistence.*;

@Entity
public class UserPost {

    @Id
    @GeneratedValue
    private int postID;

    private String description;

    @ManyToOne
    @JoinColumn(name="userId", nullable=false)
    @JsonIgnore
    private User user;

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
