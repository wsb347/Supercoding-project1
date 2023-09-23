package com.example.project01.repository.boardRepository;

import com.example.project01.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long > {
//     CRUD (Create, Read, Update, Delete) 작업을 수행 가능

    // 특정 ID로 게시물 조회 - Optional : ID에 해당하는 게시물이 존재하지 않을 경우 예외를 방지할 수 있음
    Optional<Post> findById(Long id);
    //List<Post> findByEmail(String email); 특정 email 검색
}
