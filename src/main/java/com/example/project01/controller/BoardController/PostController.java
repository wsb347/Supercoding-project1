package com.example.project01.controller.BoardController;


import com.example.project01.Entity.Post;
import com.example.project01.service.BoardService.PostService;
import com.example.project01.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    // 게시물 생성
    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    // 모든 게시물 조회
    @GetMapping("/api/main")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // 게시물 조회 by ID
    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Optional<Post> post = postService.getPostById(id);
        if (post.isPresent()) {
            return ResponseEntity.ok(post.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 게시물 수정
    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        try {
            Post post = postService.updatePost(id, updatedPost);
            return ResponseEntity.ok(post);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 게시물 삭제
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        try {
            postService.deletePost(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 추가: 이메일로 게시물 검색 API
    /*@GetMapping("/byEmail")
    public List<Post> getPostsByEmail(@RequestParam String email) {
        return postService.getPostsByEmail(email);
    }*/
}
