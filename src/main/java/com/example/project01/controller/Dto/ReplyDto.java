package com.example.project01.controller.Dto;

import com.example.project01.Entity.PostEntity;
import lombok.*;

import javax.persistence.Column;

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
