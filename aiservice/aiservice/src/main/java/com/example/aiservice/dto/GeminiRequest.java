package com.example.aiservice.dto;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j

public record GeminiRequest(List<Content> contents) {

    public static GeminiRequest fromQuestion(String question) {
        log.info("Creating GeminiRequest from question: {}", question);

        return new GeminiRequest(
                List.of(new Content(
                        List.of(new Part(question))
                ))
        );
    }

    public record Content(List<Part> parts) {}
    public record Part(String text) {}
}
