package com.study.week6.example.service;

import com.study.week6.example.model.Post;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RestClientService {

    private final RestClient restClient;

    public RestClientService(org.springframework.web.client.RestClient restClient) {
        this.restClient = restClient;
    }

    public ResponseEntity<List<Post>> getAllPosts() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<Post>>() {
                });
    }

    public Post getPost(int id) {
        return restClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .body(Post.class);
    }

    public Post createPost(Post post) {
        return restClient.post()
                .uri("/posts")
                .body(post)
                .retrieve()
                .body(Post.class);
    }

    public Post updatePost(int id, Post post) {
        return restClient.put()
                .uri("/posts/{id}", id)
                .body(post)
                .retrieve()
                .body(Post.class);
    }

    public void deletePost(int id) {
        restClient.delete()
                .uri("/posts/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }

}
