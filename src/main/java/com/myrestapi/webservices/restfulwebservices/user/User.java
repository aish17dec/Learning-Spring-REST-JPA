package com.myrestapi.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myrestapi.webservices.restfulwebservices.userPost.UserPost;
import org.hibernate.annotations.GeneratorType;
import org.springframework.cglib.core.GeneratorStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name ="user_details")
@Table(name = "USER_DETAILS")
public class User {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String about;
    private LocalDate birthday;

    //    public User(String name, int userId, String about, LocalDate birthday) {
//        this.name = name;
//        this.userId = userId;
//        this.about = about;
//        this.birthday = birthday;
//    }
    @OneToMany
    private List<UserPost> posts;

    public List<UserPost> getPosts() {
        return posts;
    }

    public void setPosts(List<UserPost> posts) {
        this.posts = posts;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userId=" + userId +
                ", about='" + about + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
