package com.example.project01.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.project01.controller.Dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Service
@Slf4j
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.issuer}")
    private String issuer;

    public static final String CLAIM_NAME_MEMBER_PASSWORD = "MemberPassword";
    private Algorithm algorithm;
    private JWTVerifier jwtVerifier;

    @PostConstruct
    private void init(){
        algorithm = Algorithm.HMAC256(secretKey);
        jwtVerifier = JWT.require(algorithm).build();
        byte[] accessSecret = secretKey.getBytes(StandardCharsets.UTF_8);
    }

    // Create JWT
    public String encode(UserDto userDto){
        LocalDateTime expiredAt = LocalDateTime.now().plusWeeks(4L);
        Date date = Timestamp.valueOf(expiredAt);

        Claims claims = Jwts.claims().setSubject(userDto.getPassword());

        return Jwts.builder().setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(date)
                .signWith(algorithm, accessSecret)
                .compact();
    }

    public Map<String, String> decode(String token){
        try{
            DecodedJWT jwt = jwtVerifier.verify(token);
            return Map.of(CLAIM_NAME_MEMBER_PASSWORD, jwt.getClaim(CLAIM_NAME_MEMBER_PASSWORD).toString());
        }catch (JWTVerificationException e){
            log.warn("Failed to decode jwt. token: {}", token, e);
            return null;
        }
    }
}
