package com.example.project01.service.ReplyService;

import com.example.project01.Entity.PostEntity;
import com.example.project01.Entity.ReplyEntity;
import com.example.project01.controller.Dto.ReplyDto;
import com.example.project01.repository.ReplyRepository.ReplyRepository;
import com.example.project01.repository.boardRepository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

        private final ReplyRepository replyRepository;
        private final PostRepository postRepository;

        public void updateReply(ReplyDto replyDto) {
                PostEntity postEntity = postRepository.findById(replyDto.getPost_id())
                        .orElseThrow(() -> new EntityNotFoundException("해당 ID의 게시물을 찾을 수 없습니다."));;

                ReplyEntity replyEntity = ReplyEntity.builder()
                        .post(postEntity)
                        .author(replyDto.getAuthor())
                        .content(replyDto.getContent())
                        .build();

                replyRepository.save(replyEntity);
        }

        public List<ReplyEntity> findByPostid(long postId) {
                return replyRepository.findAllByPost_Id(postId);
        }

        public List<ReplyEntity> findAll() {
                return replyRepository.findAllBy();
        }

        public void saveReply(ReplyDto replyDto) {
                // replyDto에서 post_id를 이용하여 실제 PostEntity를 가져옵니다.
                PostEntity postEntity = postRepository.findById(replyDto.getPost_id())
                        .orElseThrow(() -> new EntityNotFoundException("해당 ID의 게시물을 찾을 수 없습니다."));

                ReplyEntity replyEntity = ReplyEntity.builder()
                        .post(postEntity)
                        .author(replyDto.getAuthor())
                        .content(replyDto.getContent())
                        .build();

                replyRepository.save(replyEntity);
        }
}
