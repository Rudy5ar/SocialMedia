package com.socialmedia.socialmediaclone.services;

import com.socialmedia.socialmediaclone.model.Post;
import com.socialmedia.socialmediaclone.repository.PostRepository;
import com.socialmedia.socialmediaclone.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Page<Post> getPosts(int pageNumber) {

        Pageable p = PageRequest.of(pageNumber, 5);
        return postRepository.findAll(p);
    }

    public Post createPost(String description) {
        Post newPost = new Post();
        newPost.setDescription(description);
        newPost.setDateCreated(LocalDate.now());
        newPost.setIdUser(userRepository.findById(1L).orElseThrow(() -> new RuntimeException("No user")));
        postRepository.save(newPost);
        return newPost;
    }

    public Post updatePost(Post post) {
        return post;
    }

    public void deletePost(Integer postId) {
    }
}
