package com.socialmedia.socialmediaclone.controllers;

import com.socialmedia.socialmediaclone.dto.PostDTO;
import com.socialmedia.socialmediaclone.mapper.PostMapper;
import com.socialmedia.socialmediaclone.model.Post;
import com.socialmedia.socialmediaclone.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @GetMapping
    public Page<PostDTO> getPosts(@RequestParam int pageNumber, @RequestParam int pageSize) {
        Page<Post> pages = postService.getPosts(pageNumber, pageSize);
        return pages.map(postMapper::toDto);
    }

    @PostMapping(consumes = "multipart/form-data")
    public Post createPost(@RequestParam String description, @RequestParam("file") MultipartFile file) throws IOException {
        return postService.createPost(description, file);
    }

    @PutMapping
    public Post updatePost(@RequestBody Post post) {
        return postService.updatePost(post);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable long id) {
        postService.deletePost(id);
    }

    @PutMapping("likeDislikePost")
    public Post likeDislikePost(@RequestParam long idPost, @RequestParam long idUser) {
        return postService.likeDislikePost(idPost, idUser);
    }

}
