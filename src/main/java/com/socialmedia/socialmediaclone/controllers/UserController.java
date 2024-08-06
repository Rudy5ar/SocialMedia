package com.socialmedia.socialmediaclone.controllers;

import com.socialmedia.socialmediaclone.model.Comment;
import com.socialmedia.socialmediaclone.model.Following;
import com.socialmedia.socialmediaclone.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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

}
