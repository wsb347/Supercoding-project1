package com.example.project01.Controller.Dto.BoardDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PostDTO {

//    게시물 데이터를 전달할 DTO 클래스 - getter와 setter 생성

    private Long id;
    private String author;
    private String title;
    private String content;

    public PostDTO(Long id, String author, String title, String content) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
