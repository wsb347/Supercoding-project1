package com.example.project01.Controller;

import com.example.project01.Entity.Heart;
import com.example.project01.Service.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class HeartController {

    private final HeartService heartService;

    @PostMapping("/like")
    public ResponseEntity<Heart> addHeart(
            @RequestParam(value="user_id") int userId,
            @RequestParam(value="post_id") int postId

    ) throws IOException {
        Heart heart = Heart.builder().userId(userId).postId(postId).build();
        Heart result = heartService.addHeart(heart);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteHeart(
            @RequestParam(value="user_id") int userId,
            @RequestParam(value = "post_id") int postId

    ) throws IOException {
        Heart heart = Heart.builder().userId(userId).postId(postId).build();

        heartService.removeHeart(heart);

        return new ResponseEntity<String>("ok",HttpStatus.CREATED);
    }

}