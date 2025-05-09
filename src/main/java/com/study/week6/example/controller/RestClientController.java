package com.study.week6.example.controller;

import com.study.week6.example.model.Post;
import com.study.week6.example.service.RestClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest-client")
public class RestClientController {

    private final RestClientService restClientService;

    public RestClientController(RestClientService restClientService) {
        this.restClientService = restClientService;
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts() {
        return restClientService.getAllPosts();
    }

    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable int id) {
        return restClientService.getPost(id);
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post) {
        return restClientService.createPost(post);
    }

    @PutMapping("/posts/{id}")
    public Post updatePost(@PathVariable int id, @RequestBody Post post) {
        return restClientService.updatePost(id, post);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable int id) {
        restClientService.deletePost(id);
    }

}
