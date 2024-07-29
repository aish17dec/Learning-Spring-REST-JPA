package com.myrestapi.webservices.restfulwebservices.controller;

import com.myrestapi.webservices.restfulwebservices.repository.model.User;
import com.myrestapi.webservices.restfulwebservices.service.UserService;
import com.myrestapi.webservices.restfulwebservices.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userDaoService;

    @GetMapping(path = "/users")
    public List<User> getAllUsers(){
        return userDaoService.getAllUsers();
    }
    @PostMapping(path = "/createUser")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> addUser(@Validated @RequestBody User user){
        userDaoService.addUser(user);
        HttpHeaders headers  = new HttpHeaders();
        headers.add("test_custom_header", "test_value");
        return new ResponseEntity<>(user,headers, HttpStatus.CREATED);
        //return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping(path = "/users/{id}")
    public User getUserById(@PathVariable int id){
        User user =  userDaoService.getUser(id);
        System.out.print("user" + user);
        if(user == null)
            throw new UserNotFoundException("No user present with id : "+id);
        return user;
    }

    @DeleteMapping(path = "/deleteUser/{id}")
    public ResponseEntity<Integer> deleteUser(@PathVariable int id){
        userDaoService.deleteUser(id);
        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "/updateUser")
    public ResponseEntity<String> updateUser(@RequestParam(required = false) String name, @RequestParam(required = false, name = "about") String about,
                                             @RequestParam(required = true) String id, @RequestParam(required = false) String birthday){
        userDaoService.updateUser(Integer.parseInt(id), name,about,LocalDate.parse(birthday));
        return new ResponseEntity<String>("User updated!", HttpStatus.ACCEPTED);
    }
    @PostMapping(path = "/signUp")
    public ResponseEntity<String> signUp(@RequestBody UserCredentialsDto signUpDetails){
        userDaoService.signUpUser(signUpDetails);
        return new ResponseEntity<>("User SignUp successful", HttpStatus.CREATED);
    }
}
