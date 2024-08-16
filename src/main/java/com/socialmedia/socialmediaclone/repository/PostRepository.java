package com.socialmedia.socialmediaclone.repository;

import com.socialmedia.socialmediaclone.model.Post;
import com.socialmedia.socialmediaclone.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Page<Post> findByUserOrderByDateCreatedDesc(User user, Pageable pageable);
    List<Post> findByUserOrderByDateCreatedDesc(User user);
    @Query("SELECT p FROM Post p WHERE p.user IN :users ORDER BY p.dateCreated DESC")
    Page<Post> findByUsersOrderByDateCreatedDesc(@Param("users") List<User> users, Pageable pageable);


}
