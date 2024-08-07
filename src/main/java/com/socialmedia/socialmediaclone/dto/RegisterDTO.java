package com.socialmedia.socialmediaclone.dto;

import lombok.Builder;

@Builder
public record RegisterDTO(String email, String username, String password) {
}
