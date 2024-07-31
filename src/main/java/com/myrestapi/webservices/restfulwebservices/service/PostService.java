package com.myrestapi.webservices.restfulwebservices.service;

import com.myrestapi.webservices.restfulwebservices.dto.PostDto;

public interface PostService {
    void createPost(String userName, PostDto post);

    PostDto updatePost(PostDto userPost);

    void remove(String userName, String postId);

    PostDto getPost(String userName, String postId);
}
