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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Long id;

    private String username;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @ManyToMany(mappedBy = "likedUsers")
    private List<Comment> likedComments = new ArrayList<>();

    @OneToMany(mappedBy = "follower")
    private List<Following> following = new ArrayList<>();

    @OneToMany(mappedBy = "followed")
    private List<Following> followers = new ArrayList<>();
}
