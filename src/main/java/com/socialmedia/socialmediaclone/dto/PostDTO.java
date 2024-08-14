package com.socialmedia.socialmediaclone.dto;

import com.socialmedia.socialmediaclone.model.Comment;
import lombok.Builder;

import java.util.List;

@Builder
public record PostDTO(long id, int totalLikes, String image, String description, String dateCreated, String user, List<CommentDTO> comments) {
}
