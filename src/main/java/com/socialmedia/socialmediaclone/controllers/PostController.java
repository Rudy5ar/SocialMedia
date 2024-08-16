package com.socialmedia.socialmediaclone.controllers;

import com.socialmedia.socialmediaclone.dto.PostDTO;
import com.socialmedia.socialmediaclone.mapper.PostMapper;
import com.socialmedia.socialmediaclone.model.Post;
import com.socialmedia.socialmediaclone.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable String postId) {
        return new ResponseEntity<>(postMapper.toDto(postService.getPost(Integer.parseInt(postId.substring(1, 2)))), HttpStatus.OK);
    }

    @GetMapping("getFollowedPosts")
    public ResponseEntity<Page<PostDTO>> getFollowedPosts(@RequestParam int pageNumber, @RequestParam int pageSize) {
        Page<Post> pages = postService.getFollowedPosts(pageNumber, pageSize);
        return new ResponseEntity<>(pages.map(postMapper::toDto), HttpStatus.OK);
    }

    @GetMapping("getPrivatePosts")
    public ResponseEntity<Page<PostDTO>> getPrivatePosts(@RequestParam int pageNumber, @RequestParam int pageSize) {
        Page<Post> pages = postService.getPosts(pageNumber, pageSize);
        return new ResponseEntity<>(pages.map(postMapper::toDto), HttpStatus.OK);
    }

    @GetMapping("getPostsForUser")
    public ResponseEntity<Page<PostDTO>> getPostsForUser(@RequestParam String username, @RequestParam int pageNumber, @RequestParam int pageSize) {
        Page<Post> pages = postService.getPostsForUser(username, pageNumber, pageSize);
        return new ResponseEntity<>(pages.map(postMapper::toDto), HttpStatus.OK);
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Post> createPost(@RequestParam String description, @RequestBody MultipartFile file) {
        try {
            return new ResponseEntity<>(postService.createPost(description, file), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable int postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("likeDislikePost")
    public ResponseEntity<Boolean> likeDislikePost(@RequestParam int idPost) {
        if (postService.likeDislikePost(idPost) != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("isLiked")
    public ResponseEntity<Boolean> isLiked(@RequestParam int idPost) {
        if (postService.isLiked(idPost) != null) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

}
