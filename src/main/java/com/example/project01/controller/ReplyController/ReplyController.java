package com.example.project01.controller.ReplyController;

import com.example.project01.Entity.PostEntity;
import com.example.project01.Entity.ReplyEntity;
import com.example.project01.service.ReplyService.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/comments")
    public List<ReplyEntity> findAll(){
        return replyService.findAll();
    }
    @GetMapping("/comments/{post_id}")
    public List<ReplyEntity> findByPostid(@RequestParam long post_id){


        return replyService.findByPostid(post_id);
    }
}
