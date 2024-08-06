package com.socialmedia.socialmediaclone.repository;

import com.socialmedia.socialmediaclone.model.Following;
import com.socialmedia.socialmediaclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowingRepository extends JpaRepository<Following, Long> {
    Optional<Following> findByFollowerAndFollowed(User follower, User followed);
}
