package com.heitormbonfim.www.magic_frige.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeminiService {
    private final WebClient webClient;
    private final String apiKey = System.getenv("GEMINI_API_KEY");
   public GeminiService(WebClient webClient) {
        this.webClient = webClient;
   }

    public void generateRecipe() {}
}
