package com.myrestapi.webservices.restfulwebservices.controller;

import com.myrestapi.webservices.restfulwebservices.dto.PostDto;
import com.myrestapi.webservices.restfulwebservices.dto.UserDto;
import com.myrestapi.webservices.restfulwebservices.repository.model.UserModel;
import com.myrestapi.webservices.restfulwebservices.repository.model.PostModel;
import com.myrestapi.webservices.restfulwebservices.service.PostService;
import com.myrestapi.webservices.restfulwebservices.service.UserService;
import com.myrestapi.webservices.restfulwebservices.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/user/post")
public class PostController {

    private final PostService postService;

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> createPost(@PathVariable String userName, @RequestBody PostDto post){
        postService.createPost(userName, post);
        return new ResponseEntity<>("New Post Added for user!", HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<PostDto>> getAllPostsForUser(@PathVariable String userName){
        List<PostDto> userPosts = userService.getAllPosts(userName);
        return new ResponseEntity<>(userPosts, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<PostDto> getPost(@PathVariable String userName, @PathVariable String postId){
        PostDto postDto = postService.getPost(userName, postId);
        return new ResponseEntity<>(postDto, HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<PostDto> editPost(@PathVariable String userName, @RequestBody PostDto postDto){
        postService.updatePost(postDto);
        return new ResponseEntity<>(postDto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public void deletePost(@PathVariable String userName, @PathVariable String postId){
        postService.remove(userName, postId);
    }
}
