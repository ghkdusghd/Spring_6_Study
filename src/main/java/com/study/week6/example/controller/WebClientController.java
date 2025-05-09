package com.study.week6.example.controller;

import com.study.week6.example.model.Post;
import com.study.week6.example.service.WebClientService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/web-client")
public class WebClientController {

    private final WebClientService webClientService;

    public WebClientController(WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    @GetMapping("/posts")
    public Flux<Post> getAllPosts() {
        return webClientService.getPosts();
    }

}
