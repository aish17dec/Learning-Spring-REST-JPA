package com.myrestapi.webservices.restfulwebservices.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    public String getUserNameFromJWT(String token);

    public boolean validateToken(String token, UserDetails userDetails);

    public String generateToken(UserDetails userDetails);

    public String generateRefreshToken(UserDetails userDetails);
}
