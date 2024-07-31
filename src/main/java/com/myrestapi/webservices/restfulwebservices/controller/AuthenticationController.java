package com.myrestapi.webservices.restfulwebservices.controller;

import com.myrestapi.webservices.restfulwebservices.dto.JWTAuthenticationResponseDto;
import com.myrestapi.webservices.restfulwebservices.dto.SignInRequestDto;
import com.myrestapi.webservices.restfulwebservices.dto.SignUpRequestDto;
import com.myrestapi.webservices.restfulwebservices.dto.UserDto;
import com.myrestapi.webservices.restfulwebservices.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @PostMapping("/signup/user")
    public ResponseEntity<UserDto> signUpUser(SignUpRequestDto userDto) {
        return new ResponseEntity<>(authenticationService.signUpUser(userDto), HttpStatus.CREATED);
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<String> signUpAdmin(SignUpRequestDto userDto) {
        return new ResponseEntity<>(authenticationService.signUpAdmin(userDto), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthenticationResponseDto> signIn(SignInRequestDto signInRequestDto) {
        return new ResponseEntity<>(authenticationService.login(signInRequestDto), HttpStatus.OK);

    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JWTAuthenticationResponseDto> refreshToken(String refreshToken) {
        return new ResponseEntity<>(authenticationService.refreshToken(refreshToken), HttpStatus.OK);

    }
}
