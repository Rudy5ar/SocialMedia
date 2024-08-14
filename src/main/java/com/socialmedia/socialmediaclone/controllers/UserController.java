package com.socialmedia.socialmediaclone.controllers;

import com.socialmedia.socialmediaclone.dto.UserDTO;
import com.socialmedia.socialmediaclone.mapper.UserMapper;
import com.socialmedia.socialmediaclone.model.Following;
import com.socialmedia.socialmediaclone.model.User;
import com.socialmedia.socialmediaclone.repository.UserRepository;
import com.socialmedia.socialmediaclone.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserMapper userMapper, UserRepository userRepository) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll().stream().map(userMapper::toDto).toList(), HttpStatus.OK);
    }

    @PostMapping("follow")
    public ResponseEntity<Following> follow(@RequestParam long userId, @RequestParam long followerId) {
        try {
            return new ResponseEntity<>(userService.follow(userId, followerId), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("acceptFollow")
    public ResponseEntity<Boolean> acceptFollow(@RequestParam long userId, @RequestParam long toFollowId) {
        try {
            return new ResponseEntity<>(userService.acceptFollow(userId, toFollowId), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/currentUser")
    public ResponseEntity<UserDTO> allUsers() {
        User currentUser = userService.getCurrentUser();
        return ResponseEntity.ok(userMapper.toDto(currentUser));

    }

}
