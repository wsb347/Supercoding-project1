package com.example.project01.controller.BoardController;


import com.example.project01.Entity.PostEntity;
import com.example.project01.controller.Dto.BoardDTO.PostDTO;
import com.example.project01.service.BoardService.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/posts") // 게시글을 생성하는 api
    public String postBoard(@ModelAttribute PostDTO postDTO) {
        // PostDTO에서 데이터 추출
        String title = postDTO.getTitle();
        String contents = postDTO.getContents();

        // 게시물 생성 및 저장
        PostEntity newPost = new PostEntity(title, contents); // 예시: Post 생성자에 맞게 변경 필요
        return postService.save(newPost).toString(); // PostService에 게시물 저장 로직 추가

//        return "redirect:/api/posts"; // 게시물 목록 페이지로 리다이렉트
    }

    @GetMapping("/posts") //전체 게시글을 출력하는 api - Main화면에 전송
    public String getBoard() {
        // 전체 데이터 Get
        return postService.findAll().toString();

//        return "redirect:/api/posts"; // 게시물 목록 페이지로 리다이렉트
    }

    @PutMapping("/posts/{post_id}") //게시글을 수정하는 api
    public String putBoard(@ModelAttribute PostDTO postDTO, @PathVariable Long post_id) {
        String title = postDTO.getTitle();
        String contents = postDTO.getContents();

        // postService.updatePost 메서드 호출하고 반환값 저장
        return postService.updatePost(post_id, title, contents);
    }


    @GetMapping("/posts/{post_id}") // 특정 ID를 받아와 게시글을 출력하는 api
    public ResponseEntity<PostEntity> getPostById(@PathVariable Long postId) {
        Optional<PostEntity> postOptional = postService.getById(postId);

        if (postOptional.isPresent()) { //객체가 값(게시물)을 가지고 있는지 여부를 확인
            return ResponseEntity.ok(postOptional.get()); //객체가 값(데이터)을 포함하고 있으면, 그 값을 반환
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
