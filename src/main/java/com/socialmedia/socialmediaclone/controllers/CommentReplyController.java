package com.socialmedia.socialmediaclone.controllers;

import com.socialmedia.socialmediaclone.dto.ReplyDTO;
import com.socialmedia.socialmediaclone.mapper.ReplyMapper;
import com.socialmedia.socialmediaclone.model.CommentReply;
import com.socialmedia.socialmediaclone.repository.UserRepository;
import com.socialmedia.socialmediaclone.services.CommentReplyService;
import com.socialmedia.socialmediaclone.services.CommentService;
import com.socialmedia.socialmediaclone.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/commentreply")
public class CommentReplyController {

    private final CommentReplyService commentReplyService;
    private final ReplyMapper replyMapper;
    private final CommentService commentService;
    private final UserRepository userRepository;
    private final UserService userService;

    public CommentReplyController(CommentReplyService commentReplyService, ReplyMapper replyMapper, CommentService commentService, UserRepository userRepository, UserService userService) {
        this.commentReplyService = commentReplyService;
        this.replyMapper = replyMapper;
        this.commentService = commentService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("createReply")
    public ResponseEntity<ReplyDTO> createReply(@RequestParam String text, @RequestParam long commentId) {
        return new ResponseEntity<>(
                replyMapper.toDto(
                        commentReplyService.createReply(
                                text,
                                userRepository.findByUsername(userService.getCurrentUser().getUsername()).orElseThrow(() -> new RuntimeException("No such user")).getId(),
                                commentId)),
                HttpStatus.OK);
    }

    @GetMapping("getRepliesForComment")
    public ResponseEntity<Page<ReplyDTO>> getRepliesForComment(@RequestParam long commentId, @RequestParam int pageNumber, @RequestParam int pageSize) {
        Page<CommentReply> replies = commentReplyService.getRepliesForComment(commentId, pageNumber, pageSize);
        return new ResponseEntity<>(replies.map(replyMapper::toDto), HttpStatus.OK);
    }

    @PatchMapping("likeDislikeReply")
    public ResponseEntity<ReplyDTO> likeDislikeReply(@RequestParam String username, @RequestParam long replyId) {
        return new ResponseEntity<>(replyMapper.toDto(commentService.likeDislikeReply(
                userRepository.findByUsername(username).orElseThrow(
                        () -> new RuntimeException("No such user")).getId(), replyId)),
                HttpStatus.OK);
    }

}
