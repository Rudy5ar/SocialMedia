package com.socialmedia.socialmediaclone.dto;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record PostDTO(int totalLikes, String image, String description) {
}
