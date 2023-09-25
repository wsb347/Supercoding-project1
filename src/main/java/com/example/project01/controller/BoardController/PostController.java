package com.example.project01.controller.BoardController;


import com.example.project01.entity.Post;
import com.example.project01.service.BoardService.PostService;
import com.example.project01.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> createPost(@RequestBody Post post) {

        Post createdPost = postService.createPost(post);
        if (createdPost != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("게시물이 생성되었습니다");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시물 생성에 실패했습니다");
        }
    }

    // 모든 게시물 조회
    @GetMapping("/posts")
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

    //추가: 이메일로 게시물 검색 API
    @GetMapping("/byEmail/{email}")
    public List<Post> getPostByAuthor(@PathVariable(name = "email") String author) {
        // author 매개변수를 사용하여 로직 수행
        return postService.getPostsByAuthor(author);
    }
}
