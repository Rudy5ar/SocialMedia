package com.socialmedia.socialmediaclone.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CommentDTO(long id, String text, Integer numOfLikes, String username, List<ReplyDTO> replies) {
}
