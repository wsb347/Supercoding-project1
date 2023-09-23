package com.example.project01.Repository;

import com.example.project01.Entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {



    Optional<Heart> findByUserIdAndPostId(int userId, int postId);
}
