package com.socialmedia.socialmediaclone.mapper;

import com.socialmedia.socialmediaclone.dto.CommentDTO;
import com.socialmedia.socialmediaclone.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentDTO toDto(Comment c) {
        return CommentDTO.builder()
                .text(c.getText())
                .numOfLikes(c.getNumOfLikes())
                .build();
    }

    public Comment fromDto(CommentDTO dto) {
        return Comment.builder()
                .text(dto.text())
                .numOfLikes(dto.numOfLikes())
                .build();
    }
}