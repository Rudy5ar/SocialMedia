package com.socialmedia.socialmediaclone.mapper;

import com.socialmedia.socialmediaclone.dto.ReplyDTO;
import com.socialmedia.socialmediaclone.model.CommentReply;
import org.springframework.stereotype.Component;

@Component
public class ReplyMapper {

    public ReplyDTO toDto(CommentReply r) {
        return ReplyDTO.builder()
                .id(r.getId())
                .text(r.getText())
                .numOfLikes(r.getNumOfLikes())
                .username(r.getUser().getUsername())
                .build();
    }

}
