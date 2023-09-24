package com.example.project01.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "`like`")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "post_id")
    private long postId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "email")
    private UserEntity user;
}
