package com.study.week6.example.service;

import com.study.week6.example.model.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class WebClientService {

    private final WebClient webClient;

    public WebClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Post> getPosts() {
        return webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(Post.class);
    }

}
