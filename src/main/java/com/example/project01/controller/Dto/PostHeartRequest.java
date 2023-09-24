package com.example.project01.controller.Dto;

import com.example.project01.entity.Post;
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
