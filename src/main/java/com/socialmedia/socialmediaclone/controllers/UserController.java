package com.socialmedia.socialmediaclone.controllers;

import com.socialmedia.socialmediaclone.model.Following;
import com.socialmedia.socialmediaclone.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("follow")
    public Following follow(@RequestParam long userId, @RequestParam long followerId) {
        return userService.follow(userId, followerId);
    }

    @PutMapping("acceptFollow")
    public boolean acceptFollow(@RequestParam long userId, @RequestParam long toFollowId) {
        return userService.acceptFollow(userId, toFollowId);
    }

}
