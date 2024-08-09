package com.socialmedia.socialmediaclone.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPost")
    private Long id;

    @Builder.Default
    private Integer totalLikes = 0;

    private String description;

    private LocalDate dateCreated;

    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Like> likes;
}
