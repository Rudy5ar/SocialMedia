package com.socialmedia.socialmediaclone.controllers;

import com.socialmedia.socialmediaclone.model.Comment;
import com.socialmedia.socialmediaclone.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {


    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public ResponseEntity<Comment> addComment(@RequestParam long userId, @RequestParam String content, @RequestParam long postId) {
        return new ResponseEntity<>(commentService.addComment(userId, content, postId), HttpStatus.OK);
    }

    @PatchMapping("/likeDislikeComment")
    public ResponseEntity<Comment> likeDislikeComment(@RequestParam long userId, @RequestParam long commentId) {
        return new ResponseEntity<>(commentService.likeDislikeComment(userId, commentId), HttpStatus.OK);
    }



}
