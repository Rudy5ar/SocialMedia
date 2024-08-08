package com.socialmedia.socialmediaclone.controllers;

import com.socialmedia.socialmediaclone.dto.ReplyDTO;
import com.socialmedia.socialmediaclone.mapper.ReplyMapper;
import com.socialmedia.socialmediaclone.model.CommentReply;
import com.socialmedia.socialmediaclone.services.CommentReplyService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/commentreply")
public class CommentReplyController {

    private final CommentReplyService commentReplyService;
    private final ReplyMapper replyMapper;

    public CommentReplyController(CommentReplyService commentReplyService, ReplyMapper replyMapper) {
        this.commentReplyService = commentReplyService;
        this.replyMapper = replyMapper;
    }

    @PostMapping("createReply")
    public ResponseEntity<ReplyDTO> createReply(@RequestParam String text, @RequestParam long userId, @RequestParam long postId) {
        return new ResponseEntity<>(replyMapper.toDto(commentReplyService.createReply(text, userId, postId)), HttpStatus.OK);
    }

    @GetMapping("getRepliesForComment")
    public ResponseEntity<Page<ReplyDTO>> getRepliesForComment(@RequestParam long commentId, @RequestParam int pageNumber, @RequestParam int pageSize) {
        Page<CommentReply> replies = commentReplyService.getRepliesForComment(commentId, pageNumber, pageSize);
        return new ResponseEntity<>(replies.map(replyMapper::toDto), HttpStatus.OK);
    }

}
