package com.myrestapi.webservices.restfulwebservices.userPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostDaoService {

    @Autowired
    PostRepository postRepository;

    public void createPost(UserPost post) {
        System.out.println(post.getUser());
        postRepository.save(post);
    }

    public UserPost getUserPost(int postId){
        return postRepository.findById(postId).get();
    }

    public void deleteUserPost(int postId){
        UserPost post = postRepository.findById(postId).get();
        postRepository.delete(post);
    }

    public UserPost editPost(UserPost post){
        UserPost original = postRepository.findById(post.getPostID()).get();
        original.setDescription(post.getDescription());
        postRepository.save(original);
        return original;
    }
}
