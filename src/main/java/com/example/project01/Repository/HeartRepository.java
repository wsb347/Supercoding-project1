package com.example.project01.repository;

import com.example.project01.Entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    Optional<Heart> findByUserId(String User);

//    Optional<Heart> findByUserIdAndPostId(String userId, long postId);

    @Query("SELECT h FROM Heart h WHERE h.user.email = :userId AND h.postId = :postId")
    Optional<Heart> findHeartByUserIdAndPostId(@Param("userId") String userId, @Param("postId") long postId);

}
