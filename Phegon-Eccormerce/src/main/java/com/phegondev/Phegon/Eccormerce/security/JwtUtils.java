package com.phegondev.Phegon.Eccormerce.security;

import com.phegondev.Phegon.Eccormerce.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

//JwtUtils 클래스는 스프링부트 보안 시스템에서 jwt json web token 기반 인증을 처리하기 위한
//핵심 유틸 클래스.
@Service
@Slf4j
public class JwtUtils {
    private static final long EXPIRATION_TIME_IN_MILLISEC = 1000L * 60L *60L *24L * 30L * 6L; //expirers 6 months
    private SecretKey key;

    @Value("${secreteJwtString}")
    private String secreteJwtString; //Make sure the value in the application properties is 32characters or long

    @PostConstruct
    private void init(){
        byte[] keyBytes = secreteJwtString.getBytes(StandardCharsets.UTF_8);
        this.key = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    public String generateToken(User user){
        String username = user.getEmail();
        return generateToken(username);
    }

    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MILLISEC))
                .signWith(key)
                .compact();
    }

    public String getUsernameFromToken(String token){
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
        return claimsTFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }


}
