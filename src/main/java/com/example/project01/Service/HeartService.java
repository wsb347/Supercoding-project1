package com.example.project01.service;

import com.example.project01.Entity.Heart;
import com.example.project01.Entity.UserEntity;
import com.example.project01.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class HeartService {

    private final HeartRepository heartRepository;

    public Heart addHeart(Heart heart) throws IOException {
        Heart h = Heart.builder()
                .postId(heart.getPostId())
                .user(heart.getUser())
                .build();
        return heartRepository.save(h);
    }

    public void removeHeart(Heart heart) throws IOException {
        Optional<Heart> heartOpt = heartRepository.findByUserIdAndPostId(heart.getUser().getId(), heart.getPostId());

        heartRepository.delete(heartOpt.get());
    }

//    public Optional<UserEntity> findById(long id){
//        heartRepository.findById(id);
//
//    }

}