package com.socialmedia.socialmediaclone.mapper;

import com.socialmedia.socialmediaclone.dto.PostDTO;
import com.socialmedia.socialmediaclone.model.Post;
import com.socialmedia.socialmediaclone.model.User;
import com.socialmedia.socialmediaclone.repository.LikeRepository;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class PostMapper {

    private final CommentMapper commentMapper;
    private final LikeRepository likeRepository;

    public PostMapper(CommentMapper commentMapper, LikeRepository likeRepository) {
        this.commentMapper = commentMapper;
        this.likeRepository = likeRepository;
    }

    public PostDTO toDto(Post p) {
        User u = p.getUser();
        boolean isLiked = likeRepository.findByUserAndPost(u, p) != null;
        return PostDTO.builder()
                .id(p.getId())
                .description(p.getDescription())
                .image(Base64.getEncoder().encodeToString(p.getImage()))
                .totalLikes(p.getTotalLikes())
                .dateCreated(p.getDateCreated().toString())
                .username(u.getUsername())
                .comments(p.getComments().stream().map(commentMapper::toDto).toList())
                .isLiked(isLiked)
                .build();
    }

}
