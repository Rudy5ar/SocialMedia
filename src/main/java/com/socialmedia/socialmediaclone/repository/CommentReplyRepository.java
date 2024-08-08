package com.socialmedia.socialmediaclone.repository;

import com.socialmedia.socialmediaclone.model.Comment;
import com.socialmedia.socialmediaclone.model.CommentReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentReplyRepository extends JpaRepository<CommentReply, Long> {
    Page<CommentReply> findByComment(Comment comment, Pageable pageable);
}
