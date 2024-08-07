package com.socialmedia.socialmediaclone.controllers;

import com.socialmedia.socialmediaclone.dto.PostDTO;
import com.socialmedia.socialmediaclone.mapper.PostMapper;
import com.socialmedia.socialmediaclone.model.Post;
import com.socialmedia.socialmediaclone.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/posts/")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @GetMapping("getFollowedPosts")
    public ResponseEntity<Page<PostDTO>> getFollowedPosts(@RequestParam int pageNumber, @RequestParam int pageSize) {
        try {
            Page<Post> pages = postService.getFollowedPosts(pageNumber, pageSize);
            return new ResponseEntity<>(pages.map(postMapper::toDto), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getPrivatePosts")
    public ResponseEntity<Page<PostDTO>> getPrivatePosts(@RequestParam int pageNumber, @RequestParam int pageSize) {
        try {
            Page<Post> pages = postService.getPosts(pageNumber, pageSize);
            return new ResponseEntity<>(pages.map(postMapper::toDto), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Post> createPost(@RequestParam String description, @RequestParam("file") MultipartFile file) {
        try {
            return new ResponseEntity<>(postService.createPost(description, file), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("likeDislikePost")
    public ResponseEntity<Post> likeDislikePost(@RequestParam long idPost) {
        try {
            return new ResponseEntity<>(postService.likeDislikePost(idPost), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
