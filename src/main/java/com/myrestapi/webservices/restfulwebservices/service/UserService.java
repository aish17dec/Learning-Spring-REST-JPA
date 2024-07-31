package com.myrestapi.webservices.restfulwebservices.service;

import com.myrestapi.webservices.restfulwebservices.dto.PostDto;
import com.myrestapi.webservices.restfulwebservices.dto.SignUpRequestDto;
import com.myrestapi.webservices.restfulwebservices.dto.UpdateUserDto;
import com.myrestapi.webservices.restfulwebservices.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();

    UserDto getUser(String userId);

    boolean deleteUser(String id);

    void updateUser(UpdateUserDto updateUserDto, String userName);

    List<PostDto> getAllPosts(String userName);
    List<UserDto> getAllUsersDetails();

    UserDto signUpUser(SignUpRequestDto signUpRequestDto);

    String signUpAdmin(SignUpRequestDto signUpRequestDto);
}
