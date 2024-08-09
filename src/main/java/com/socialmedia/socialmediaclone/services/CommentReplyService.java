package com.socialmedia.socialmediaclone.services;

import com.socialmedia.socialmediaclone.model.Comment;
import com.socialmedia.socialmediaclone.model.CommentReply;
import com.socialmedia.socialmediaclone.model.User;
import com.socialmedia.socialmediaclone.repository.CommentReplyRepository;
import com.socialmedia.socialmediaclone.repository.CommentRepository;
import com.socialmedia.socialmediaclone.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentReplyService {

    private final UserRepository userRepository;
    private final CommentReplyRepository commentReplyRepository;
    private final CommentRepository commentRepository;

    public CommentReplyService(UserRepository userRepository, CommentReplyRepository commentReplyRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.commentReplyRepository = commentReplyRepository;
        this.commentRepository = commentRepository;
    }

    public CommentReply createReply(String text, long userId, long commentId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("No user found"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("No comment found"));
        CommentReply commentReply = new CommentReply();
        commentReply.setText(text);
        commentReply.setComment(comment);
        commentReply.setUser(user);
        commentReply = commentReplyRepository.save(commentReply);

        comment.getReplies().add(commentReply);
        commentRepository.save(comment);

        user.getCommentReplies().add(commentReply);
        userRepository.save(user);

        return commentReply;

    }

    public Page<CommentReply> getRepliesForComment(long commentId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return commentReplyRepository.findByComment(commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("No comment found")), pageable);
    }
}
