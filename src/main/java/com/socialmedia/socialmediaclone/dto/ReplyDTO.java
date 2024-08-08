package com.socialmedia.socialmediaclone.dto;

import lombok.Builder;

@Builder
public record ReplyDTO(String text, int numOfLikes) {
}
