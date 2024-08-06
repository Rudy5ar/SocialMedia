package com.socialmedia.socialmediaclone.controllers;

import com.socialmedia.socialmediaclone.model.Comment;
import com.socialmedia.socialmediaclone.model.Post;
import com.socialmedia.socialmediaclone.model.User;
import com.socialmedia.socialmediaclone.repository.CommentRepository;
import com.socialmedia.socialmediaclone.repository.PostRepository;
import com.socialmedia.socialmediaclone.repository.UserRepository;
import com.socialmedia.socialmediaclone.services.CommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/comment")
public class CommentController {


    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public Comment addComment(@RequestParam long userId, @RequestParam String content, @RequestParam long postId) {
        return commentService.addComment(userId, content, postId);
    }

    @PutMapping("/likeDislikeComment")
    public Comment likeDislikeComment(@RequestParam long userId, @RequestParam long commentId) {
        commentService.likeDislikeComment(userId, commentId);
        return null;
    }

}
