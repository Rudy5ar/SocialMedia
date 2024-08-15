package com.socialmedia.socialmediaclone.controllers;

import com.socialmedia.socialmediaclone.dto.CommentDTO;
import com.socialmedia.socialmediaclone.mapper.CommentMapper;
import com.socialmedia.socialmediaclone.model.Comment;
import com.socialmedia.socialmediaclone.repository.UserRepository;
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
    private final UserRepository userRepository;

    public CommentController(CommentService commentService, UserService userService, CommentMapper commentMapper, UserRepository userRepository) {
        this.commentService = commentService;
        this.userService = userService;
        this.commentMapper = commentMapper;
        this.userRepository = userRepository;
    }

    @PostMapping("/commentPost")
    public ResponseEntity<CommentDTO> addComment(@RequestParam String content, @RequestParam int postId) {
        return new ResponseEntity<>(commentMapper.toDto(commentService.addComment(userService.getCurrentUser().getId(), content, postId)), HttpStatus.OK);
    }

    @PatchMapping("/likeDislikeComment")
    public ResponseEntity<CommentDTO> likeDislikeComment(@RequestParam String username, @RequestParam long commentId) {
        return new ResponseEntity<>(commentMapper.toDto(commentService.likeDislikeComment(
                userRepository.findByUsername(username).orElseThrow(
                        () -> new RuntimeException("No such user")).getId(), commentId)),
                HttpStatus.OK);
    }


}
