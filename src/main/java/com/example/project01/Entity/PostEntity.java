package com.example.project01.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Getter
@NoArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String contents;
    @javax.persistence.Id
    private Long memberId;

    public PostEntity(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public PostEntity(Long memberId, String title, String contents) {
        this.memberId = memberId;
        this.title = title;
        this.contents = contents;
    }

    public PostEntity(Long memberId){

    }


    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }


    public void setContent(String new_title, String new_contents) {
        this.title = new_title;
        this.contents = new_contents;
    }
}