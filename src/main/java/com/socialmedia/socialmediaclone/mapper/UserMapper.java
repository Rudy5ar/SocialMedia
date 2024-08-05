package com.socialmedia.socialmediaclone.mapper;

import com.socialmedia.socialmediaclone.dto.UserDTO;
import com.socialmedia.socialmediaclone.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDto(User u) {
        return UserDTO.builder()
                .email(u.getEmail())
                .username(u.getUsername())
                .build();
    }

    public User fromDto(UserDTO dto) {
        return User.builder()
                .email(dto.email())
                .username(dto.username())
                .password(dto.password())
                .build();
    }
}
