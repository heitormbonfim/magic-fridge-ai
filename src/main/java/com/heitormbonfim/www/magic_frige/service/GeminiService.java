package com.heitormbonfim.www.magic_frige.service;

import com.heitormbonfim.www.magic_frige.model.FoodItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeminiService {
    private final WebClient webClient;

    public Mono<String> generateRecipe(List<FoodItem> foodItems) {
        String foods = foodItems.stream().map(item ->
                String.format(
                        "%s (%s) - Amount: %s, dueDate: %s",
                        item.getName(),
                        item.getCategory(),
                        item.getAmount(),
                        item.getDueDate()
                )).collect(Collectors.joining());

        String prompt = "Based in the data in my database, create a recipe with the following not spoiled food items: " + foods;

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
