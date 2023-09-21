package com.example.project01.service.BoardService;

import com.example.project01.Entity.PostEntity;
import com.example.project01.repository.boardRepository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostEntity save(PostEntity newPost) {
        return postRepository.save(newPost);
    }

    public List<PostEntity> findAll() {
        return postRepository.findAll();
    }


    public String updatePost(Long postId, String newTitle, String newContent) {
        // 1. 엔티티 조회
        PostEntity postToUpdate = postRepository.findById(postId).orElse(null);

        if (postToUpdate != null) {
            // 2. 엔티티 수정
            postToUpdate.setContent(newTitle, newContent);

            // 3. 수정된 엔티티 저장
            return postRepository.save(postToUpdate).toString();
        }
        else return null;
    }

    public Optional<PostEntity> getById(Long postId){
        return postRepository.findById(postId);
    };
}
