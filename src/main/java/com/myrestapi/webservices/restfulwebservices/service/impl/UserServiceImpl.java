package com.myrestapi.webservices.restfulwebservices.service.impl;

import com.myrestapi.webservices.restfulwebservices.dto.PostDto;
import com.myrestapi.webservices.restfulwebservices.dto.SignUpRequestDto;
import com.myrestapi.webservices.restfulwebservices.dto.UpdateUserDto;
import com.myrestapi.webservices.restfulwebservices.dto.UserDto;
import com.myrestapi.webservices.restfulwebservices.enums.Role;
import com.myrestapi.webservices.restfulwebservices.exception.AdminExistsException;
import com.myrestapi.webservices.restfulwebservices.exception.UserNotFoundException;
import com.myrestapi.webservices.restfulwebservices.repository.UserRepository;
import com.myrestapi.webservices.restfulwebservices.repository.model.UserModel;
import com.myrestapi.webservices.restfulwebservices.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserDto getUser(String userName){
        UserModel user = userRepository.findByUserName(userName).get();
        if(user == null)
            throw new UserNotFoundException(userName);
        UserDto userDto = new UserDto();
        userDto.setUserName(userName);
        return userDto;
    }

    public boolean deleteUser(String userName){
        Optional<UserModel> user = userRepository.findByUserName(userName);
        if(user.isEmpty())
            throw new UserNotFoundException(userName);

        userRepository.deleteByUserName(userName);
        return true;
    }

    public void updateUser(UpdateUserDto updateUser, String userName) {
        UserModel user = userRepository.findByUserName(userName).get();

        if(user == null)
            throw new UserNotFoundException(userName);

        if(updateUser.getName()!=null)
            user.setFirstname(updateUser.getName());
        if(updateUser.getAbout()!=null)
            user.setAbout(updateUser.getAbout());
        if(updateUser.getBirthday()!=null)
            user.setBirthday(updateUser.getBirthday());
        if(updateUser.getEmailId()!=null)
            user.setEmailId(updateUser.getEmailId());
        if(updateUser.getWebsite()!=null)
            user.setWebsite(updateUser.getWebsite());
        user.setUpdatedAt(new Date());

        userRepository.save(user);
    }

    @Override
    public List<PostDto> getAllPosts(String userName) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsersDetails() {
        List<UserModel> userModels = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        userModels.forEach(userModel -> {
            UserDto userDto = new UserDto();
            userDto.setId(userModel.getId());
            userDto.setAbout(userModel.getAbout());
            userDto.setBirthday(userModel.getBirthday());
            userDto.setUserName(userModel.getUsername());
            userDto.setEmailId(userModel.getEmailId());
            userDto.setWebsite(userModel.getWebsite());
            setUserPosts(userDto, userModel);
            userDtos.add(userDto);
        });
        return userDtos;
    }

    private void setUserPosts(UserDto userDto, UserModel userModel) {
        List<PostDto> posts = new ArrayList<>();
        userModel.getPosts().forEach(postModel -> {
            PostDto postDto = new PostDto();
            postDto.setPostId(postModel.getId());
            postDto.setContent(postModel.getContent());
            postDto.setCreatedAt(postModel.getCreatedAt());
            postDto.setUpdatedAt(postModel.getUpdatedAt());
            posts.add(postDto);
        });
        userDto.setPosts(posts);
    }

    public UserDto signUpUser(SignUpRequestDto userCredentials){
        UserModel user = new UserModel();
        user.setUserName(userCredentials.getUserName());
        user.setPassword(userCredentials.getPassword());
        user.setEmailId(userCredentials.getEmailId());
        user.setFirstname(userCredentials.getFirstName());
        user.setLastname(userCredentials.getLastName());
        user.setBirthday(userCredentials.getDateOfBirth());
        user.setRole(Role.USER);
        try{
            user = userRepository.save(user);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUsername());
        userDto.setEmailId(user.getEmailId());
        userDto.setBirthday(user.getBirthday());
        userDto.setFirstName(user.getFirstname());
        userDto.setLastName(user.getLastname());
        return userDto;
    }

    @Override
    public String signUpAdmin(SignUpRequestDto signUpRequestDto) {
        List<UserModel> adminUsers = userRepository.findByRole(Role.ADMIN);
        if(adminUsers.size() > 0)
            throw new AdminExistsException("Admin already exists in the system");

        UserModel user = new UserModel();
        user.setUserName(signUpRequestDto.getUserName());
        user.setPassword(signUpRequestDto.getPassword());
        user.setEmailId(signUpRequestDto.getEmailId());
        user.setFirstname(signUpRequestDto.getFirstName());
        user.setLastname(signUpRequestDto.getLastName());
        user.setBirthday(signUpRequestDto.getDateOfBirth());
        user.setRole(Role.ADMIN);
        try{
            user = userRepository.save(user);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Admin created successfully";
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<UserModel> userModel = userRepository.findByUserName(username);
                if(!userModel.isPresent()) {
                    throw new UserNotFoundException(username);
                }
                return userModel.get();
            }
        };
    }
}
