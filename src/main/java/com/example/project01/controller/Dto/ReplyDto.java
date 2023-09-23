package com.example.project01.controller.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDto {
    private long post_id;

    @NonNull
    private String content;

    private String author;
}
