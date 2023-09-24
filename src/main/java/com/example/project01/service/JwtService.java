package com.example.project01.service;

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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtService {

    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secretKey;


    // Create JWT
    public String encode(UserDto userDto) {
            LocalDateTime expiredAt = LocalDateTime.now().plusMinutes(10);
            Date date = Timestamp.valueOf(expiredAt);

            Claims claims = Jwts.claims().setSubject(userDto.getEmail());

            return Jwts.builder().setClaims(claims)
                    .setId(userDto.getPassword())
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



