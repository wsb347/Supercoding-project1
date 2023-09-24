package com.example.project01.controller.Dto;

import com.example.project01.Entity.UserEntity;
import lombok.*;

import javax.persistence.*;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeartRequest {

    private long post_id;

    private long user_id;
}
