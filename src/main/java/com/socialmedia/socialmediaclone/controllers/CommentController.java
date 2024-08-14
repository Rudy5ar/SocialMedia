package com.socialmedia.socialmediaclone.controllers;

import com.socialmedia.socialmediaclone.dto.CommentDTO;
import com.socialmedia.socialmediaclone.mapper.CommentMapper;
import com.socialmedia.socialmediaclone.model.Comment;
import com.socialmedia.socialmediaclone.services.CommentService;
import com.socialmedia.socialmediaclone.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, UserService userService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.userService = userService;
        this.commentMapper = commentMapper;
    }

    @PostMapping("/commentPost")
    public ResponseEntity<CommentDTO> addComment(@RequestParam String content, @RequestParam String postId) {
        return new ResponseEntity<>(commentMapper.toDto(commentService.addComment(userService.getCurrentUser().getId(), content, Integer.parseInt(postId))) , HttpStatus.OK);
    }

    @PatchMapping("/likeDislikeComment")
    public ResponseEntity<Comment> likeDislikeComment(@RequestParam long userId, @RequestParam long commentId) {
        return new ResponseEntity<>(commentService.likeDislikeComment(userId, commentId), HttpStatus.OK);
    }



}
