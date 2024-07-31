package com.myrestapi.webservices.restfulwebservices.controller;

import com.myrestapi.webservices.restfulwebservices.dto.JWTAuthenticationResponseDto;
import com.myrestapi.webservices.restfulwebservices.dto.SignInRequestDto;
import com.myrestapi.webservices.restfulwebservices.dto.SignUpRequestDto;
import com.myrestapi.webservices.restfulwebservices.dto.UserDto;
import com.myrestapi.webservices.restfulwebservices.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup/user")
    public ResponseEntity<UserDto> signUpUser(@Valid @RequestBody SignUpRequestDto userDto) {
        return new ResponseEntity<>(authenticationService.signUpUser(userDto), HttpStatus.CREATED);
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<String> signUpAdmin(@Valid @RequestBody SignUpRequestDto userDto) {
        return new ResponseEntity<>(authenticationService.signUpAdmin(userDto), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthenticationResponseDto> signIn(@Valid @RequestBody SignInRequestDto signInRequestDto) {
        return new ResponseEntity<>(authenticationService.login(signInRequestDto), HttpStatus.OK);

    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JWTAuthenticationResponseDto> refreshToken(@RequestParam String refreshToken) {
        if(refreshToken == null)
            throw new IllegalArgumentException("Refresh token is required");
        return new ResponseEntity<>(authenticationService.refreshToken(refreshToken), HttpStatus.OK);

    }
}
