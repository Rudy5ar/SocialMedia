package com.socialmedia.socialmediaclone.services;

import com.socialmedia.socialmediaclone.model.Following;
import com.socialmedia.socialmediaclone.repository.FollowingRepository;
import com.socialmedia.socialmediaclone.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import com.socialmedia.socialmediaclone.model.User;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FollowingRepository followingRepository;

    public UserService(UserRepository userRepository, FollowingRepository followingRepository) {
        this.userRepository = userRepository;
        this.followingRepository = followingRepository;
    }

    public Following follow(@RequestParam long userId, @RequestParam long toFollowId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        User userToFollow = userRepository.findById(toFollowId).orElseThrow(() -> new RuntimeException("User to follow not found"));

        Following following = new Following();
        following.setFollower(user);
        following.setFollowed(userToFollow);
        followingRepository.save(following);

        return following;
    }
}
