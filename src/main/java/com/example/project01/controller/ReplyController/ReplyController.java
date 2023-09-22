package com.example.project01.controller.ReplyController;

import com.example.project01.Entity.PostEntity;
import com.example.project01.Entity.ReplyEntity;
import com.example.project01.controller.Dto.ReplyDto;
import com.example.project01.controller.Response;
import com.example.project01.service.JwtService;
import com.example.project01.service.ReplyService.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReplyController {

    private final ReplyService replyService;
    private  final JwtService jwtService;

    @GetMapping("/comments")
    public List<ReplyEntity> findAll(){
        return replyService.findAll();
    }

    @GetMapping("/comments/{post_id}")
    public List<ReplyEntity> findByPostid(@PathVariable long post_id){
        return replyService.findByPostid(post_id);
    }


    @PostMapping("/comments")
    public ResponseEntity<?> createReply(@RequestBody ReplyDto replyDto, @RequestHeader("Token") String token) {
        String author = jwtService.extractUserId(token);
        replyDto.setAuthor(author);

        replyService.saveReply(replyDto);
        return ResponseEntity.ok("댓글이 성공적으로 작성되었습니다.");
    }

    @PutMapping("/comments/:comment_id")
    public ResponseEntity<?> updateReply(@RequestBody ReplyDto replyDto, @RequestHeader("Token") String token) {
        String author = jwtService.extractUserId(token);
        replyDto.setAuthor(author);

        replyService.updateReply(replyDto);
        return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
    }

}
