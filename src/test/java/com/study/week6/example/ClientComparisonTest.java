package com.study.week6.example;

import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.client.RestClient;
import reactor.core.publisher.Flux;

import java.util.List;

public class ClientComparisonTest {

    WebClient webClient = WebClient.builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .build();

    RestClient restClient = RestClient.builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .build();

    public Flux<Post> getPostsWithWebClient() {
        System.out.println("[WebClient] 시작: " + Thread.currentThread().getName());

        return webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(Post.class)
                .doOnNext(post -> System.out.println("[WebClient] 응답 처리 중: " + post.id()
                        + " / " + Thread.currentThread().getName()))
                .doOnComplete(() -> System.out.println("[WebClient] 완료"));
    }

    public ResponseEntity<List<Post>> getPostsWithRestClient() {
        System.out.println("[RestClient] 시작: " + Thread.currentThread().getName());

        ResponseEntity<List<Post>> response = restClient.get()
                .uri("/posts")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {});

        System.out.println("[RestClient] 완료: " + Thread.currentThread().getName());
        return response;
    }

    @Test
    public void testWebClientAsync() throws InterruptedException {
        System.out.println("[Main] 시작 : " + Thread.currentThread().getName());

        // 비동기 실행 확인
        getPostsWithWebClient().subscribe();
        getPostsWithWebClient().subscribe();

        // WebClient는 비동기이므로 메인 스레드 종료 방지를 위해 대기
        Thread.sleep(2000);
        System.out.println("[Main] 종료 : " + Thread.currentThread().getName());
    }

    @Test
    public void testRestClientSync() {
        System.out.println("[Main] 시작 : " + Thread.currentThread().getName());

        // 동기 실행 확인
        ResponseEntity<List<Post>> response1 = getPostsWithRestClient();
        System.out.println("[RestClient] response1 응답 받은 게시글 수: " + response1.getBody().size());
        ResponseEntity<List<Post>> response2 = getPostsWithRestClient();
        System.out.println("[RestClient] response2 응답 받은 게시글 수: " + response2.getBody().size());

        System.out.println("[Main] 종료 : " + Thread.currentThread().getName());
    }

    public record Post(int userId, int id, String title, String body) {}
}
