package org.example.ai.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ai.config.GroqConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Lightweight wrapper over the Groq Chat Completions API so other services can
 * simply pass prompts and receive the model response text.
 */
@Component
public class GroqClient {

    private final GroqConfig config;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    public GroqClient(GroqConfig config) {
        this.config = config;
    }

    public String ask(String prompt) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(config.apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);

            JsonNode body = mapper.createObjectNode()
                    .put("model", config.model)
                    .set("messages", mapper.createArrayNode()
                            .add(mapper.createObjectNode()
                                    .put("role", "user")
                                    .put("content", prompt)));

            HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(body), headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    config.apiUrl,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            JsonNode content = mapper.readTree(response.getBody())
                    .path("choices")
                    .path(0)
                    .path("message")
                    .path("content");

            if (content.isMissingNode() || content.isNull()) {
                throw new IllegalStateException("Groq response did not contain content");
            }

            return content.asText();
        } catch (Exception ex) {
            throw new RuntimeException("Failed to call Groq API", ex);
        }
    }
}
