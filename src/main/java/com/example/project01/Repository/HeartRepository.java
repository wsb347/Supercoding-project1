package com.example.project01.repository;

import com.example.project01.Entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    Optional<Heart> findByUserId(String User);

    Optional<Heart> findByUserIdAndPostId(String userId, long postId);
}
