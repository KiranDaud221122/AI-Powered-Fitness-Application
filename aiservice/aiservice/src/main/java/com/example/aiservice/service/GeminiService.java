package com.example.aiservice.service;

import com.example.aiservice.dto.GeminiRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
@Slf4j
@Service
public class GeminiService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String getAnswer(String question) {
        log.info("Sending question to Gemini API: {}", question);

        GeminiRequest request = GeminiRequest.fromQuestion(question);

        return webClient.post()
                .uri(geminiApiUrl + "?key=" + geminiApiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException(
                                        "Gemini API error: " + body
                                ))
                )
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(10))
                .doOnError(error -> log.error("Error calling Gemini API: {}", error.getMessage()))
                .block();
    }
}
