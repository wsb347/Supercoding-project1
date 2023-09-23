package com.example.project01.Entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "heart")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Heart {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "post_id")
    @NonNull
    private int postId;

    @Column(name="user_id")
    @NonNull
    private int userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
