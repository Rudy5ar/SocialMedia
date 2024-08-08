package com.socialmedia.socialmediaclone.mapper;

import com.socialmedia.socialmediaclone.dto.PostDTO;
import com.socialmedia.socialmediaclone.model.Post;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostDTO toDto(Post p) {
        return PostDTO.builder()
                .description(p.getDescription())
                .image(Base64.encodeBase64String(p.getImage()))
                .totalLikes(p.getTotalLikes())
                .build();
    }

}
