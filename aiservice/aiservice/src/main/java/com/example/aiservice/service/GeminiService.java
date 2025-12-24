package com.example.aiservice.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class GeminiService {

    public final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;


    public GeminiService(WebClient.Builder webClientBuilder){
        this.webClient=webClientBuilder.build();
    }

     public String getRecommandations(String details){
         Map<String , Object> requestBody = Map.of("contents", new Object[] {
                 Map.of("parts",new Object[] {
                         Map.of("text", details)
                 })
         });

         String response = webClient.post()
                 .uri(geminiApiUrl)
                 .header("Content-Type","application/json")
                 .header("x-goog-api-key", geminiApiKey)
                 .bodyValue(requestBody)
                 .retrieve()
                 .bodyToMono(String.class)
                 .block();

         return response;
     }

}
