package com.socialmedia.socialmediaclone.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComment")
    private Long id;

    @Column(nullable = false)
    private String text;

    @Builder.Default
    @Column(nullable = false)
    private Integer numOfLikes = 0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idPost", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "likedcomment",
            joinColumns = @JoinColumn(name = "idComment"),
            inverseJoinColumns = @JoinColumn(name = "idUser")
    )
    @Builder.Default
    private List<User> likedUsers = new ArrayList<>();

    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
    private List<CommentReply> replies = new ArrayList<>();
}
