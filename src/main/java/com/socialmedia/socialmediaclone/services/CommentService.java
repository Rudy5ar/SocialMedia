package com.socialmedia.socialmediaclone.services;

import com.socialmedia.socialmediaclone.model.Comment;
import com.socialmedia.socialmediaclone.model.Post;
import com.socialmedia.socialmediaclone.model.User;
import com.socialmedia.socialmediaclone.repository.CommentRepository;
import com.socialmedia.socialmediaclone.repository.PostRepository;
import com.socialmedia.socialmediaclone.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Service
public class CommentService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentService(PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Comment addComment(@RequestParam long userId, @RequestParam String content, @RequestParam long postId) {
        Post post = postRepository.findById(postId).orElseThrow(RuntimeException::new);
        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setText(content);
        Comment savedComment = commentRepository.save(comment);
        post.getComments().add(savedComment);
        postRepository.save(post);
        user.getComments().add(savedComment);
        userRepository.save(user);
        return savedComment;
    }

    @Transactional
    public Comment likeDislikeComment(long userId, long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if(!comment.getLikedUsers().contains(user)) {
            return likeComment(comment, user);
        }

        return dislikeComment(comment, user);
    }

    @Transactional
    public Comment likeComment(Comment comment, User user) {
        comment.getLikedUsers().add(user);
        comment.setNumOfLikes(comment.getNumOfLikes() + 1);

        user.getLikedComments().add(comment);
        userRepository.save(user);

        return commentRepository.save(comment);
    }

    @Transactional
    public Comment dislikeComment(Comment comment, User user) {
        comment.getLikedUsers().remove(user);
        comment.setNumOfLikes(comment.getNumOfLikes() - 1);

        user.getLikedComments().remove(comment);
        userRepository.save(user);

        return commentRepository.save(comment);
    }
}
