package com.myrestapi.webservices.restfulwebservices.service.impl;

import com.myrestapi.webservices.restfulwebservices.dto.PostDto;
import com.myrestapi.webservices.restfulwebservices.repository.PostRepository;
import com.myrestapi.webservices.restfulwebservices.repository.model.PostModel;
import com.myrestapi.webservices.restfulwebservices.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public void createPost(String userName, PostDto post) {
        //TODO - implement create post logic
    }

    public PostModel getUserPost(String postId){
        return postRepository.findById(postId).get();
    }

    public void deleteUserPost(String postId){
        PostModel post = postRepository.findById(postId).get();
        postRepository.delete(post);
    }

    public PostDto updatePost(PostDto post){
        //TODO- implement update post logic
        return null;
    }

    @Override
    public void remove(String userName, String postId) {
        //TODO- implement delete post logic
    }

    @Override
    public PostDto getPost(String userName, String postId) {
        //TODO- implement get post logic
        return null;
    }
}
