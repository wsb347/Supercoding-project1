package com.example.project01.controller.Dto;

import com.example.project01.Entity.Heart;
import com.example.project01.Entity.Post;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostHeartRequest {

    private Post post;
    private HeartRequest heartRequest;
    private long heart;
}
