package com.socialmedia.socialmediaclone.repository;

import com.socialmedia.socialmediaclone.model.Like;
import com.socialmedia.socialmediaclone.model.Post;
import com.socialmedia.socialmediaclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    void deleteByUserAndPost(User user, Post post);
    Like findByUserAndPost(User user, Post post);
}
