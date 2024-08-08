package com.socialmedia.socialmediaclone.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "commentreply")
public class CommentReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCommentReply", nullable = false)
    private Long id;

    @Size(max = 45)
    @NotNull
    private String text;

    @NotNull
    @Builder.Default
    private Integer numOfLikes = 0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idComment", nullable = false)
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @OneToMany(mappedBy = "commentReply", fetch = FetchType.LAZY)
    private List<LikedReply> likedReplies = new ArrayList<>();
}
