package com.socialmedia.socialmediaclone.controllers;

import com.socialmedia.socialmediaclone.model.Following;
import com.socialmedia.socialmediaclone.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
