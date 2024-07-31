package com.myrestapi.webservices.restfulwebservices.service;

import com.myrestapi.webservices.restfulwebservices.dto.JWTAuthenticationResponseDto;
import com.myrestapi.webservices.restfulwebservices.dto.SignInRequestDto;
import com.myrestapi.webservices.restfulwebservices.dto.SignUpRequestDto;
import com.myrestapi.webservices.restfulwebservices.dto.UserDto;

public interface AuthenticationService {
    public UserDto signUpUser(SignUpRequestDto signUpRequestDto);

    public String signUpAdmin(SignUpRequestDto signUpRequestDto);

    public JWTAuthenticationResponseDto login(SignInRequestDto signInRequestDto);

    public JWTAuthenticationResponseDto refreshToken(String refreshToken);
}
