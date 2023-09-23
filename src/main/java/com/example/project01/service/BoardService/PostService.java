package com.example.project01.service.BoardService;

import com.example.project01.Entity.Post;
import com.example.project01.repository.boardRepository.PostRepository;
import com.example.project01.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // 게시물 생성
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // 모든 게시물 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 게시물 조회 by ID
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    // 게시물 수정
    public Post updatePost(Long id, Post updatedPost) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent()) {
            Post post = existingPost.get();
            post.setTitle(updatedPost.getTitle());
            post.setContent(updatedPost.getContent());
            return postRepository.save(post);
        } else {
            throw new NotFoundException("게시물을 찾을 수 없습니다.");
        }
    }

    // 게시물 삭제
    public void deletePost(Long id) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent()) {
            postRepository.deleteById(id);
        } else {
            throw new NotFoundException("게시물을 찾을 수 없습니다.");
        }
    }

    // 특정 이메일 로검색
   /* public List<Post> getPostsByEmail(String email) {
        return postRepository.findByEmail(email);
    } */
}

