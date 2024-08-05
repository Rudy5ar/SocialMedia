package com.socialmedia.socialmediaclone.services;

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

import java.time.LocalDate;


@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
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
        return postRepository.save(post);
    }

    public void deletePost(long postId) {
        postRepository.delete(postRepository.findById(postId).orElseThrow(() -> new RuntimeException("No post found with id: " + postId)));
    }

    @Transactional
    public Post likePost(long idPost, long idUser) {
        Post post = postRepository.findById(idPost).orElseThrow(() -> new RuntimeException("No post found with id: " + idPost));
        Like like = new Like();
        like.setIdUser(userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("No user")));
        like.setIdPost(post);
        likeRepository.save(like);

        post.getLikes().add(like);
        postRepository.save(post);
        return post;
    }

    public Post dislikePost(long idPost, long idUser) {
        Post post = postRepository.findById(idPost).orElseThrow(() -> new RuntimeException("No post found with id: " + idPost));
        User user = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("No user"));
        post.setTotalLikes(post.getTotalLikes() - 1);
        likeRepository.deleteByUserAndPost(user, post);
        return post;
    }
}
