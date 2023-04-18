package com.myrestapi.webservices.restfulwebservices.userPost;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<UserPost, Integer> {
}
