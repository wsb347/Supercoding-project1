package com.example.project01.service.ReplyService;

import com.example.project01.Entity.PostEntity;
import com.example.project01.Entity.ReplyEntity;
import com.example.project01.controller.Dto.ReplyDto;
import com.example.project01.controller.Response;
import com.example.project01.repository.ReplyRepository.ReplyRepository;
import com.example.project01.repository.UserRepository.UserRepository;
import com.example.project01.repository.boardRepository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {

        private final ReplyRepository replyRepository;
        private final PostRepository postRepository;


        public List<ReplyEntity> findByPostid(long postId) {
                Optional<PostEntity> postOptional = postRepository.findById(3L); // 3는 확인하려는 ID입니다.
                PostEntity post = postOptional.orElseThrow(() -> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다."));

//                return replyRepository.findAllByPost_id(postId);
                return replyRepository.findAllBy();
        }

        public List<ReplyEntity> findAll() {
                return replyRepository.findAllBy();
        }
}
