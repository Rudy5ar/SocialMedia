package com.socialmedia.socialmediaclone.mapper;

import com.socialmedia.socialmediaclone.dto.PostDTO;
import com.socialmedia.socialmediaclone.model.Post;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class PostMapper {

    private final CommentMapper commentMapper;

    public PostMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public PostDTO toDto(Post p) {
        return PostDTO.builder()
                .id(p.getId())
                .description(p.getDescription())
                .image(Base64.getEncoder().encodeToString(p.getImage()))
                .totalLikes(p.getTotalLikes())
                .dateCreated(p.getDateCreated().toString())
                .user(p.getUser().getUsername())
                .comments(p.getComments().stream().map(commentMapper::toDto).toList())
                .build();
    }

}
