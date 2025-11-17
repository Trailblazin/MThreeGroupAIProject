package org.example.ai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GroqConfig {
    @Value("${groq.api.key}")
    public String apiKey;

    @Value("${groq.api.url}")
    public String apiUrl;

    @Value("${groq.model}")
    public String model;
}
