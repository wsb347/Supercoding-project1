package com.example.project01.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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
    }

    // Create JWT
    public String encode(String password){
        LocalDateTime expiredAt = LocalDateTime.now().plusWeeks(4L);
        Date date = Timestamp.valueOf(expiredAt);

        return JWT.create()
                .withIssuer(issuer)
                .withClaim(CLAIM_NAME_MEMBER_PASSWORD, password)
                .withExpiresAt(date)
                .sign(algorithm);
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
