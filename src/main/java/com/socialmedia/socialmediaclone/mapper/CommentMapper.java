package com.socialmedia.socialmediaclone.mapper;

import com.socialmedia.socialmediaclone.dto.CommentDTO;
import com.socialmedia.socialmediaclone.model.Comment;
import com.socialmedia.socialmediaclone.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    private final ReplyMapper replyMapper;

    public CommentMapper(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }

    public CommentDTO toDto(Comment c) {
        return CommentDTO.builder()
                .id(c.getId())
                .text(c.getText())
                .numOfLikes(c.getNumOfLikes())
                .username(c.getUser().getUsername())
                .replies(c.getReplies().stream()
                        .map(replyMapper::toDto)
                        .toList())
                .build();
    }
}