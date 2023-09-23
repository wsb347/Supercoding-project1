package com.example.project01.controller;

import com.example.project01.Entity.Heart;
import com.example.project01.Entity.UserEntity;
import com.example.project01.controller.Dto.HeartRequest;
import com.example.project01.service.HeartService;
import com.example.project01.repository.UserRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class HeartController {

    private final HeartService heartService;
    private final UserRepository userRepository;

    @PostMapping("/like")
    public ResponseEntity<Heart> addHeart(
            @RequestBody HeartRequest heartRequest
    ) throws IOException {

        // userId로 UserEntity 찾기 (예를 들어, UserRepository를 사용)
        Optional<UserEntity> optionalUserEntity = userRepository.findById(heartRequest.getUser_id());
        if (!optionalUserEntity.isPresent()) {
            // 적절한 예외 처리
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserEntity userEntity = optionalUserEntity.get();

        // 이제 userEntity를 사용하여 Heart 객체 생성
        Heart heart = Heart.builder()
                .user(userEntity)
                .postId(heartRequest.getPost_id())
                .build();

        Heart result = heartService.addHeart(heart);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/like")
    public ResponseEntity<String> deleteHeart(
            @RequestBody HeartRequest heartRequest

    ) throws IOException {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(heartRequest.getUser_id());

        UserEntity userEntity = optionalUserEntity.get();

        Heart heart = Heart.builder().user(userEntity).postId(heartRequest.getPost_id()).build();

        heartService.removeHeart(heart);

        return new ResponseEntity<String>("ok",HttpStatus.CREATED);
    }

}