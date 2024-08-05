package com.socialmedia.socialmediaclone.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
    @Column(name = "idPost", nullable = false)
    private Integer id;

    @Builder.Default
    private Integer totalLikes = 0;

    @Column(name = "description", length = 150)
    private String description;

    @Column(name = "dateCreated", nullable = false)
    private LocalDate dateCreated;

    @Column(name = "image")
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUser", nullable = false)
    private User idUser;

    @OneToMany(mappedBy = "idPost")
    private List<Comment> comments;

    @OneToMany(mappedBy = "idPost")
    private Set<Like> likes = new LinkedHashSet<>();

}