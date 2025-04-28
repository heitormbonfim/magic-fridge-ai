package com.heitormbonfim.www.magic_frige.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {
    private final WebClient webClient;
    private final String apiKey = System.getenv("GEMINI_API_KEY");
   public GeminiService(WebClient webClient) {
        this.webClient = webClient;
   }

    public Mono<String> generateRecipe() {
        String prompt = "Qual a origem da palavra cálculo?";
        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of(
                                "parts", List.of(
                                        Map.of("text", prompt)
                                )
                        )
                )
        );

        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class);
    }
}
