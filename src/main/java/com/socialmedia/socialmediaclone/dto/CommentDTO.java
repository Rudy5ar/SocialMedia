package com.socialmedia.socialmediaclone.dto;

import lombok.Builder;

@Builder
public record CommentDTO(String text, Integer numOfLikes) {
}
