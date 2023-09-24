package com.example.project01.service.ReplyService;

import com.example.project01.entity.Post;
import com.example.project01.entity.ReplyEntity;
import com.example.project01.controller.Dto.ReplyDto;
import com.example.project01.repository.ReplyRepository.ReplyRepository;
import com.example.project01.repository.boardRepository.PostRepository;
import com.example.project01.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
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
                return replyRepository.findAllByPost_Id(postId);
        }

        public List<ReplyEntity> findAll() {
                return replyRepository.findAllBy();
        }

        public void saveReply(ReplyDto replyDto) {
                Post post = postRepository.findById(replyDto.getPost_id())
                        .orElseThrow(() -> new EntityNotFoundException("해당 ID의 게시물을 찾을 수 없습니다."));

                ReplyEntity replyEntity = ReplyEntity.builder()
                        .post(post)
                        .author(replyDto.getAuthor())
                        .content(replyDto.getContent())
                        .build();

                replyRepository.save(replyEntity);
        }


        public ReplyEntity updateReply(ReplyDto replyDto, long id) {
                // 댓글 ID를 통해 데이터베이스에서 기존 댓글을 찾습니다.
                Post post = postRepository.findById(replyDto.getPost_id())
                        .orElseThrow(() -> new EntityNotFoundException("해당 ID의 게시물을 찾을 수 없습니다."));

                Optional<ReplyEntity> existingReply = replyRepository.findById(id);
                if (existingReply.isPresent()) {
                        ReplyEntity replyEntity = existingReply.get();
                        replyEntity.setContent(replyDto.getContent());
                        return replyRepository.save(replyEntity);
                } else {
                        throw new NotFoundException("댓글을 찾을 수 없습니다.");
                }
        }

        public void deleteReply(ReplyDto replyDto, long id) {
                Optional<ReplyEntity> existingReply = replyRepository.findById(id);
                if (existingReply.isPresent()) {
                        ReplyEntity replyEntity = existingReply.get();
                        replyRepository.deleteById(replyEntity.getId());

                } else {
                        throw new NotFoundException("게시물을 찾을 수 없습니다.");
                }
        }

}