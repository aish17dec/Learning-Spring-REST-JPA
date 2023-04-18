package com.myrestapi.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class UserDaoService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCredentialRepository userCredentialRepository;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(int userId){
        try{
            return userRepository.findById(userId).get();
        }catch (NoSuchElementException e){
            return null;
        }
    }

    public void addUser(User user){
        /*user.setUserId(UserDaoService.id);
        UserDaoService.id++;
        users.add(user);*/

        userRepository.save(user);
    }

    public int deleteUser(int userId){
        userRepository.deleteById(userId);
        return userId;
    }

    public void updateUser(Integer id, String name, String about, LocalDate birthday) {
        User user = userRepository.findById(id).get();
        if(name!=null)
            user.setName(name);
        if(about!=null)
            user.setAbout(about);
        if(birthday!=null)
            user.setBirthday(birthday);

        userRepository.save(user);
    }

    public void signUpUser(UserCredentials userCredentials){
        String encodedPassword = Base64.getEncoder().encodeToString(userCredentials.getPassword().getBytes());
        userCredentials.setPassword(encodedPassword);
        userCredentialRepository.save(userCredentials);
    }
}
