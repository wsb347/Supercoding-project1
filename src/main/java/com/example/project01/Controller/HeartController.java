package com.example.project01.controller;

import com.example.project01.Entity.Heart;
import com.example.project01.Entity.UserEntity;
import com.example.project01.controller.Dto.HeartRequest;
import com.example.project01.controller.Dto.PostHeartRequest;
import com.example.project01.repository.UserRepository.UserRepository;
import com.example.project01.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class HeartController {

    private final HeartService heartService;
    private final UserRepository userRepository;

    @PostMapping("/like")
    public ResponseEntity<PostHeartRequest> addHeart(@RequestBody HeartRequest heartRequest) {

        PostHeartRequest result = heartService.addHeart(heartRequest);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/like")
    public ResponseEntity<String> deleteHeart(@RequestBody HeartRequest heartRequest) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail((heartRequest.getUser_id()));

        if (!optionalUserEntity.isPresent()) {
            return new ResponseEntity<>("일치하는 회원이 없습니다.", HttpStatus.NOT_FOUND);
        }

        UserEntity userEntity = optionalUserEntity.get();
        Heart heart = Heart.builder().user(userEntity).postId(heartRequest.getPost_id()).build();

        try {
            heartService.removeHeart(heart);
            return new ResponseEntity<>("좋아요가 삭제되었습니다.", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}