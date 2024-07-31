package com.myrestapi.webservices.restfulwebservices.service.impl;

import com.myrestapi.webservices.restfulwebservices.dto.PostDto;
import com.myrestapi.webservices.restfulwebservices.dto.UpdateUserDto;
import com.myrestapi.webservices.restfulwebservices.dto.UserCredentialsDto;
import com.myrestapi.webservices.restfulwebservices.dto.UserDto;
import com.myrestapi.webservices.restfulwebservices.exception.UserNotFoundException;
import com.myrestapi.webservices.restfulwebservices.repository.UserRepository;
import com.myrestapi.webservices.restfulwebservices.repository.model.UserModel;
import com.myrestapi.webservices.restfulwebservices.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Base64;
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

    public void addUser(UserModel user){
        userRepository.save(user);
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

        userRepository.save(user);
    }

    @Override
    public List<PostDto> getAllPosts(String userName) {
        return null;
    }

    public void signUpUser(UserCredentialsDto userCredentials){
        //TODO - Add logic to validate password
        String encodedPassword = Base64.getEncoder().encodeToString(userCredentials.getPassword().getBytes());
        userCredentials.setPassword(encodedPassword);
    }

    //==================================updated code starts here========================================================

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
