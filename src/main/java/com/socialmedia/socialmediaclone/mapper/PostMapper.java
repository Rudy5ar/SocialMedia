package com.socialmedia.socialmediaclone.mapper;

import com.socialmedia.socialmediaclone.dto.CommentDTO;
import com.socialmedia.socialmediaclone.dto.PostDTO;
import com.socialmedia.socialmediaclone.model.Comment;
import com.socialmedia.socialmediaclone.model.Post;

public class PostMapper {
    public PostDTO toDto(Post p) {
        return PostDTO.builder()
                .description(p.getDescription())
                .image(p.getImage())
                .totalLikes(p.getTotalLikes())
                .build();
    }

    public Post fromDto(PostDTO dto) {
        return Post.builder()
                .description(dto.description())
                .image(dto.image())
                .totalLikes(dto.totalLikes())
                .build();
    }
}
