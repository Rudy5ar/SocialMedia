package com.socialmedia.socialmediaclone.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
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

    @Column(name = "totalLikes", nullable = false)
    private Integer totalLikes;

    @Column(name = "description", length = 150)
    private String description;

    @Column(name = "image")
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUser", nullable = false)
    private User idUser;

    @OneToMany(mappedBy = "idPost")
    private Set<Comment> comments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idPost")
    private Set<Like> likes = new LinkedHashSet<>();

}