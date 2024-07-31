package com.myrestapi.webservices.restfulwebservices.service.impl;

import com.myrestapi.webservices.restfulwebservices.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {

    @Override
    public String getUserNameFromJWT(String token) {
        return extractClaim(token, Claims::getSubject); // returns userName in this case
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromJWT(token);
        if(username.equals(userDetails.getUsername()) && !isTokenExpired(token)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();

    }

    private Key getSignInKey() {
        byte[] key = Decoders.BASE64.decode("413F4428472B4B6250655368566D5970337336763979244226452948404D6351");
        return Keys.hmacShaKeyFor(key);
    }
    @Override
    public String generateToken(UserDetails userDetails) {
        String token = Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey()).compact();
        return token;
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails) {
        String refreshToken = Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 7))
                .signWith(getSignInKey()).compact();
        return refreshToken;
    }
}
