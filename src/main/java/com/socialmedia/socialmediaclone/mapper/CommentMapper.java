package com.socialmedia.socialmediaclone.mapper;

import com.socialmedia.socialmediaclone.dto.CommentDTO;
import com.socialmedia.socialmediaclone.model.Comment;
import com.socialmedia.socialmediaclone.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    private final UserRepository userRepository;

    public CommentMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CommentDTO toDto(Comment c) {
        return CommentDTO.builder()
                .text(c.getText())
                .numOfLikes(c.getNumOfLikes())
                .user(c.getUser().getUsername())
                .build();
    }

    public Comment fromDto(CommentDTO dto) {
        return Comment.builder()
                .text(dto.text())
                .numOfLikes(dto.numOfLikes())
                .user(userRepository.findByUsername(dto.user()).orElseThrow(() -> new RuntimeException("No user with username to map")))
                .build();
    }
}