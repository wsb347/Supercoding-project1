package com.example.project01.repository.ReplyRepository;

import com.example.project01.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {

     public List<ReplyEntity>  findAllBy();

    List<ReplyEntity> findAllByPost_Id(long postId);
}
