package com.socialmedia.socialmediaclone.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComment;

    private String text;

    private Integer numOfLikes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Post idPost;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User idUser;

}