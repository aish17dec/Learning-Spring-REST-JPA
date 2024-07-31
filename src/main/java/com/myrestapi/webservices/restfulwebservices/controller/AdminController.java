package com.myrestapi.webservices.restfulwebservices.controller;

import com.myrestapi.webservices.restfulwebservices.dto.UserDto;
import com.myrestapi.webservices.restfulwebservices.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final UserService userService;

    @GetMapping(path = "/all-users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsersDetails(), HttpStatus.ACCEPTED);
    }
}
