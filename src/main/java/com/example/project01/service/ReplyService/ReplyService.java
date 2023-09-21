package com.example.project01.service.ReplyService;

import com.example.project01.Entity.ReplyEntity;
import com.example.project01.repository.ReplyRepository.ReplyRepository;
import com.example.project01.repository.boardRepository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

        private final ReplyRepository replyRepository;
        private final PostRepository postRepository;


        public List<ReplyEntity> findByPostid(long postId) {
                return replyRepository.findAllByPost_Id(postId);
        }

        public List<ReplyEntity> findAll() {
                return replyRepository.findAllBy();
        }
}
