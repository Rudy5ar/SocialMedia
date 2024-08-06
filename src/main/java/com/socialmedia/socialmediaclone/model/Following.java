package com.socialmedia.socialmediaclone.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor@Table(name = "Following")
public class Following {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFollowing")
    private Long idFollowing;

    @ManyToOne
    @JoinColumn(name = "idUser1")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "idUser2")
    private User followed;

    @Column(name = "pending")
    @Builder.Default
    private boolean pending = true;

}
