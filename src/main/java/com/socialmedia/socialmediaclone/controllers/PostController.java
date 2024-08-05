package com.socialmedia.socialmediaclone.controllers;

import com.socialmedia.socialmediaclone.dto.PostDTO;
import com.socialmedia.socialmediaclone.mapper.PostMapper;
import com.socialmedia.socialmediaclone.model.Post;
import com.socialmedia.socialmediaclone.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @GetMapping("{pageNumber}")
    public Page<PostDTO> getPosts(@PathVariable int pageNumber){
        Page<Post> pages = postService.getPosts(pageNumber);
        return pages.map(postMapper::toDto);
    }

    @PostMapping("")
    public Post createPost(@RequestParam String description){
        return postService.createPost(description);
    }

    @PutMapping("")
    public Post updatePost(@RequestBody Post post){
        return postService.updatePost(post);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable int id){
        postService.deletePost(id);
    }

}
