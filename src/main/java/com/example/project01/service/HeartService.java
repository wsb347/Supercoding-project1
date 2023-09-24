package com.example.project01.service;

import com.example.project01.Entity.Heart;
import com.example.project01.Entity.Post;
import com.example.project01.Entity.UserEntity;
import com.example.project01.controller.Dto.HeartRequest;
import com.example.project01.controller.Dto.PostHeartRequest;
import com.example.project01.repository.HeartRepository;
import com.example.project01.repository.UserRepository.UserRepository;
import com.example.project01.repository.boardRepository.PostRepository;
import com.example.project01.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class HeartService {
    private final PostRepository postRepository;
    private final HeartRepository heartRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostHeartRequest addHeart(HeartRequest heartRequest) {
        System.out.println(userRepository.findByEmail(heartRequest.getUser_id()));
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(heartRequest.getUser_id());
        optionalUserEntity.orElseThrow(() -> new NotFoundException("유저를 찾을 수 없습니다."));
        Optional<Post> optionalPostEntity = postRepository.findById(heartRequest.getPost_id());
        Post post = optionalPostEntity.orElseThrow(() -> new NotFoundException("게시물을 찾을 수 없습니다."));

        var isHeart = heartRepository.findHeartByUserIdAndPostId(heartRequest.getUser_id(), heartRequest.getPost_id());
        if(isHeart.isPresent()){
            throw new RuntimeException("이미 좋아요를 눌렀습니다.");
        }
        Heart heart = Heart.builder()
                .user(optionalUserEntity.get())
                .postId(optionalPostEntity.get().getId())
                .build();
        heartRepository.save(heart);

        long heartCount = heartRepository.countByPostId(heart.getPostId());
        PostHeartRequest postHeartResponse = PostHeartRequest.builder()
                .post(post)
                .heartRequest(heartRequest)
                .heart(heartCount)
                .build();

        return postHeartResponse;

    }

    public void removeHeart(Heart heart) throws IOException {
        Optional<Heart> heartOpt = heartRepository.findHeartByUserIdAndPostId(heart.getUser().getEmail(), heart.getPostId());

        if (!heartOpt.isPresent()) {
            throw new IOException("좋아요 정보를 찾을 수 없습니다.");
        }

        long deleteHeart = heartOpt.get().getId();
        heartRepository.deleteById(deleteHeart);
    }


}
