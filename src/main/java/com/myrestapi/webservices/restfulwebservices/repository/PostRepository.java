package com.myrestapi.webservices.restfulwebservices.repository;

import com.myrestapi.webservices.restfulwebservices.repository.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostModel, String> {
}
