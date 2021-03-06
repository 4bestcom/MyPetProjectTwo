package com.hibernatetest.lesson.service.impl;

import com.hibernatetest.lesson.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@EnableScheduling
public class SchedulerServiceImpl implements SchedulerService {

    @Value("${scheduler.url}")
    private String url;

    @Override
    @Async
    @Scheduled(fixedDelay = 2800000)
    public void sendRequest() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        CompletableFuture<HttpResponse<String>> httpResponseCompletableFuture = client.sendAsync(builder, HttpResponse.BodyHandlers.ofString());
        try {
            log.info("scheduler is completed, code: {}", httpResponseCompletableFuture.get().statusCode());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
