package com.example.project01.controller.Dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {
    private long post_id;

    @NonNull
    private String content;

    private String author;
}
