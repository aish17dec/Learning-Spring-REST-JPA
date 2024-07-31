package com.myrestapi.webservices.restfulwebservices.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    public String getUserNameFromJWT(String token);

    boolean validateToken(String token, UserDetails userDetails);
}
