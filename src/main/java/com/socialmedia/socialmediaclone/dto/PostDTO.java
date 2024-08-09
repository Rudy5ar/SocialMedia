package com.socialmedia.socialmediaclone.dto;

import lombok.Builder;

@Builder
public record PostDTO(int totalLikes, String image, String description) {
}
