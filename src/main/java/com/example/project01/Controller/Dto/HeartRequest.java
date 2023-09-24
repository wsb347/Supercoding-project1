package com.example.project01.controller.Dto;

import lombok.*;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeartRequest {

    private long post_id;

    private String user_id;
}
