package com.socialmedia.socialmediaclone.dto;

import lombok.Builder;

@Builder
public record ReplyDTO(long id, String text, int numOfLikes, String username) {
}
