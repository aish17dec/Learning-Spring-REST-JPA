package com.myrestapi.webservices.restfulwebservices.controller;

import com.myrestapi.webservices.restfulwebservices.dto.UpdateUserDto;
import com.myrestapi.webservices.restfulwebservices.dto.UserDto;
import com.myrestapi.webservices.restfulwebservices.exception.UserNotFoundException;
import com.myrestapi.webservices.restfulwebservices.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public UserDto getUserById(@RequestParam String userName){
        UserDto user =  userService.getUser(userName);
        System.out.print("user" + user);
        if(user == null)
            throw new UserNotFoundException(userName);
        return user;
    }

    @DeleteMapping(path = "/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String userName){
        userService.deleteUser(userName);
        return new ResponseEntity<>(userName, HttpStatus.NO_CONTENT);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody UpdateUserDto updateUserDto, @RequestParam String userName){
        userService.updateUser(updateUserDto, userName);
        return new ResponseEntity<String>("User updated!", HttpStatus.ACCEPTED);
    }
}
