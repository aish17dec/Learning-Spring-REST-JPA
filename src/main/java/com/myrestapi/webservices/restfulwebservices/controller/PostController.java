package com.myrestapi.webservices.restfulwebservices.controller;

import com.myrestapi.webservices.restfulwebservices.repository.model.User;
import com.myrestapi.webservices.restfulwebservices.repository.model.UserPost;
import com.myrestapi.webservices.restfulwebservices.service.UserService;
import com.myrestapi.webservices.restfulwebservices.exception.UserNotFoundException;
import com.myrestapi.webservices.restfulwebservices.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postDaoService;

    @Autowired
    UserService userDaoService;

    @PostMapping(path = "/users/{userId}/posts")
    public ResponseEntity<String> createPost(@PathVariable Integer userId, @RequestBody UserPost post){
        User user = userDaoService.getUser(userId);
        if(user==null)
            throw new UserNotFoundException("No user found for Id "+userId);

        post.setUser(user);
        user.getPosts().add(post);
        postDaoService.createPost(post);
        return new ResponseEntity<>("New Post Added for user!", HttpStatus.CREATED);
    }

    @GetMapping(path = "/users/{userId}/posts")
    public ResponseEntity<List<UserPost>> getAllPostsForUser(@PathVariable Integer userId){
        User user = userDaoService.getUser(userId);

        if(user==null)
            throw new UserNotFoundException("No user found for Id \"+userId");

        List<UserPost> userPosts = user.getPosts();
        return new ResponseEntity<>(userPosts, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/users/{userId}/posts/{postId}")
    public ResponseEntity<UserPost> editUserPost(@PathVariable Integer userId, @PathVariable Integer postId, @RequestBody UserPost updatePost){
        User user = userDaoService.getUser(userId);

        if(user==null)
            throw new UserNotFoundException("No user found for Id \"+userId");

        List<UserPost> userPosts = user.getPosts();
        UserPost userPost = userPosts.stream().filter(p->p.getPostID() == postId).findFirst().get();
        userPost.setDescription(updatePost.getDescription());
        userPost = postDaoService.editPost(userPost);

        return new ResponseEntity<>(userPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/user/{userId}/posts/{postId}")
    public void deletePost(@PathVariable Integer userId, @PathVariable Integer postId){
        User user = userDaoService.getUser(userId);

        if(user==null)
            throw new UserNotFoundException("No user found for Id \"+userId");

        List<UserPost> userPosts = user.getPosts();
        UserPost userPost = userPosts.stream().filter(p->p.getPostID() == postId).findFirst().get();
        userPosts.remove(userPost);
        postDaoService.deleteUserPost(postId);
    }
}
