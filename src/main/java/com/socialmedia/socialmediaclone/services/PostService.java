package com.socialmedia.socialmediaclone.services;

import com.socialmedia.socialmediaclone.dto.PostDTO;
import com.socialmedia.socialmediaclone.model.Following;
import com.socialmedia.socialmediaclone.model.Like;
import com.socialmedia.socialmediaclone.model.Post;
import com.socialmedia.socialmediaclone.model.User;
import com.socialmedia.socialmediaclone.repository.LikeRepository;
import com.socialmedia.socialmediaclone.repository.PostRepository;
import com.socialmedia.socialmediaclone.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserRepository userRepository, LikeRepository likeRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.userService = userService;
    }

    public Page<Post> getPosts(int pageNumber, int pageSize) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
        return postRepository.findByUserOrderByDateCreatedDesc(userService.getCurrentUser(), p);
    }

    public Page<Post> getFollowedPosts(int pageNumber, int pageSize) {
        User currentUser = userService.getCurrentUser();

        List<User> followedUsers = currentUser.getFollowing().stream()
                .filter(following -> !following.isPending())
                .map(Following::getFollowed)
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Post> list = postRepository.findByUsersOrderByDateCreatedDesc(followedUsers, pageable);

        return list;
    }

    public Post createPost(String description, MultipartFile file) throws IOException {
        Post newPost = new Post();
        for(int i = 0; i < 100; i++){
            newPost.setDescription(description);
            newPost.setImage(file.getBytes());
            newPost.setDateCreated(LocalDate.now());
            newPost.setUser(userRepository.findById(userService.getCurrentUser().getId()).orElseThrow(() -> new RuntimeException("No username")));
            postRepository.save(newPost);
        }
        return newPost;
    }

    public void deletePost(int postId) {
        postRepository.delete(getPost(postId));
    }

    @Transactional
    public Post likeDislikePost(int idPost) {
        Post post = getPost(idPost);
        User user = userRepository.findById(userService.getCurrentUser().getId()).orElseThrow(() -> new RuntimeException("User not found"));

        for (Like like : post.getLikes()) {
            if (like.getUser().equals(user)) {
                return dislikePost(user, post);
            }
        }

        return likePost(user, post);
    }

    @Transactional
    public Post likePost(User user, Post post) {
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        likeRepository.save(like);

        post.getLikes().add(like);
        post.setTotalLikes(post.getTotalLikes() + 1);
        postRepository.save(post);
        return post;
    }

    @Transactional
    public Post dislikePost(User user, Post post) {
        Like like = likeRepository.findByUserAndPost(user, post);
        post.setTotalLikes(post.getTotalLikes() - 1);
        post.getLikes().remove(like);
        postRepository.save(post);
        likeRepository.delete(like);
        return post;
    }

    public Like isLiked(int idPost) {
        return likeRepository.findByUserAndPost(userService.getCurrentUser(), getPost(idPost));
    }

    public Page<Post> getPostsForUser(String username, int pageNumber, int pageSize) {
        return postRepository.findByUserOrderByDateCreatedDesc(
                userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("No user found")),
                PageRequest.of(pageNumber, pageSize));
    }

    public Post getPost(int postId) {
        return postRepository.findById(postId).orElseThrow(() -> new RuntimeException("No post found"));
    }
}
