package com.example.project01.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.project01.controller.Dto.UserDto;
import com.example.project01.repository.UserRepository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class JwtService {

    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secretKey;

//    @Value("${jwt.issuer}")
//    private String issuer;
//
//    public static final String CLAIM_NAME_MEMBER_PASSWORD = "MemberPassword";
//    private Algorithm algorithm;
//    private JWTVerifier jwtVerifier;

//    @PostConstruct
//    private void init(){
//        algorithm = Algorithm.HMAC256(secretKey);
//        jwtVerifier = JWT.require(algorithm).build();
//
//    }

    // Create JWT
    public String encode(UserDto userDto) {
        LocalDateTime expiredAt = LocalDateTime.now().plusWeeks(4L);
        Date date = Timestamp.valueOf(expiredAt);

        Claims claims = Jwts.claims().setSubject(userDto.getEmail());

        return Jwts.builder().setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    public boolean isTokenExpired(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
            Date expirationDate = claims.getExpiration();
            Date currentDate = new Date();

            return expirationDate.before(currentDate); // false
        } catch (ExpiredJwtException e) {

            return true;
        }
    }

    public boolean isPresent(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        String email = claims.getSubject();

        return userRepository.findByEmail(email).isPresent();
        // DB에 존재하면 true,
        // 없으면 false



    }

    public String extractUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject(); // 토큰의 subject 클레임에서 사용자 ID를 얻음
    }


    }
//    public Map<String, String> decode(String token){
//        try{
//            DecodedJWT jwt = jwtVerifier.verify(token);
//            return Map.of(CLAIM_NAME_MEMBER_PASSWORD, jwt.getClaim(CLAIM_NAME_MEMBER_PASSWORD).toString());
//        }catch (JWTVerificationException e){
//            log.warn("Failed to decode jwt. token: {}", token, e);
//            return null;
//        }


