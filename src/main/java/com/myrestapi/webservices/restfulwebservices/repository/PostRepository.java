package com.myrestapi.webservices.restfulwebservices.repository;

import com.myrestapi.webservices.restfulwebservices.repository.model.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<UserPost, Integer> {
}
