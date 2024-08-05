package com.socialmedia.socialmediaclone.dto;

import lombok.Builder;

@Builder
public record PostDTO(int totalLikes, byte[] image, String description) {
}
