package com.socialmedia.socialmediaclone.services;

import com.socialmedia.socialmediaclone.model.Following;
import com.socialmedia.socialmediaclone.model.User;
import com.socialmedia.socialmediaclone.repository.FollowingRepository;
import com.socialmedia.socialmediaclone.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Transactional
    public boolean acceptFollow(long userId, long toFollowId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        User followed = userRepository.findById(toFollowId).orElseThrow(() -> new RuntimeException("User to follow not found"));
        Following request = followingRepository.findByFollowerAndFollowed(user, followed).orElseThrow(() -> new RuntimeException("Follow request not found"));

        request.setPending(false);
        followingRepository.save(request);
        user.getFollowing().add(request);
        followed.getFollowers().add(request);
        userRepository.save(user);
        userRepository.save(followed);
        return true;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails userDetails)) {
            throw new RuntimeException("User not logged in");
        }

        String username = userDetails.getUsername();
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }
}
