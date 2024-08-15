package com.socialmedia.socialmediaclone.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PostDTO(long id, int totalLikes, String image, String description, String dateCreated, String username, List<CommentDTO> comments, boolean isLiked) {
}
