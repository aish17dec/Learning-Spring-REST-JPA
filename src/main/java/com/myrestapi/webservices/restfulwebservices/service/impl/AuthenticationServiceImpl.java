package com.myrestapi.webservices.restfulwebservices.service.impl;

import com.myrestapi.webservices.restfulwebservices.dto.JWTAuthenticationResponseDto;
import com.myrestapi.webservices.restfulwebservices.dto.SignInRequestDto;
import com.myrestapi.webservices.restfulwebservices.dto.SignUpRequestDto;
import com.myrestapi.webservices.restfulwebservices.dto.UserDto;
import com.myrestapi.webservices.restfulwebservices.service.AuthenticationService;
import com.myrestapi.webservices.restfulwebservices.service.JWTService;
import com.myrestapi.webservices.restfulwebservices.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public UserDto signUpUser(SignUpRequestDto signUpRequestDto) {
        String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        signUpRequestDto.setPassword(encodedPassword);
        return userService.signUpUser(signUpRequestDto);
    }

    @Override
    public String signUpAdmin(SignUpRequestDto signUpRequestDto) {
        String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        signUpRequestDto.setPassword(encodedPassword);
        return userService.signUpAdmin(signUpRequestDto);
    }

    @Override
    public JWTAuthenticationResponseDto login(SignInRequestDto signInRequestDto) {
        //Authenticate if the username & password pair is valid
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequestDto.getUserName(), signInRequestDto.getPassword()));

        //If so, generate the tokens using userDetails
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(signInRequestDto.getUserName());
        String token = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        JWTAuthenticationResponseDto jwtTokenDto = new JWTAuthenticationResponseDto();
        jwtTokenDto.setRefreshToken(refreshToken);
        jwtTokenDto.setToken(token);
        return jwtTokenDto;
    }

    @Override
    public JWTAuthenticationResponseDto refreshToken(String refreshToken) {
        String userNameFromJWT = jwtService.getUserNameFromJWT(refreshToken);
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userNameFromJWT);

        if(jwtService.validateToken(refreshToken, userDetails)){
            String token = jwtService.generateToken(userDetails);
            JWTAuthenticationResponseDto jwtTokenDto = new JWTAuthenticationResponseDto();
            jwtTokenDto.setRefreshToken(refreshToken);
            jwtTokenDto.setToken(token);
            return jwtTokenDto;
        }

        return null;
    }
}
