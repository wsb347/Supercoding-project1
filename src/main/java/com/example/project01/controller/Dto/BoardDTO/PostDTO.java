package com.example.project01.controller.Dto.BoardDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {

//    게시물 데이터를 전달할 DTO 클래스 - getter와 setter 생성

    private String title;
    private String contents;
}
