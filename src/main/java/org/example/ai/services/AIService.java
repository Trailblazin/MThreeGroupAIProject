package org.example.ai.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ai.client.GroqClient;
import org.example.ai.entities.AnalyzeLogResponse;
import org.example.ai.entities.AlertAnalysisResponse;
import org.example.ai.entities.AnomalyDetectionResponse;
import org.example.ai.entities.DevOpsChatResponse;
import org.example.ai.entities.FixRecommendationResponse;
import org.example.ai.entities.IncidentSummaryResponse;
import org.example.ai.entities.IncidentTimelineResponse;
import org.example.ai.utils.PromptBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AIService {

    private final GroqClient client;
    private final PromptBuilder promptBuilder;
    private final ObjectMapper mapper = new ObjectMapper();

    public AIService(GroqClient client, PromptBuilder promptBuilder) {
        this.client = client;
        this.promptBuilder = promptBuilder;
    }

    private <T> T askModel(String prompt, Class<T> clazz, String operation) {
        try {
            String result = cleanJsonResponse(client.ask(prompt));
            // Check if the result is valid JSON
            if (!isValidJson(result)) {
                throw new RuntimeException("Model response is not valid JSON: " + result);
            }
            return mapper.readValue(result, clazz);
        } catch (Exception e) {
            System.err.println("Error occurred during " + operation + " with prompt: " + prompt);
            throw new RuntimeException("Failed to parse " + operation + " JSON", e);
        }
    }

    // BASIC CHAT
    public String chat(String message) {
        return client.ask(message);
    }

    // MAIN LOG ANALYSIS
    public AnalyzeLogResponse analyzeLogs(String logs) {
        return askModel(
                promptBuilder.buildAnalysisPrompt(logs),
                AnalyzeLogResponse.class,
                "analyzeLogs"
        );
    }

    // ALERT INTELLIGENCE (classification, grouping, RCA)
    public AlertAnalysisResponse analyzeAlerts(String logs) {
        return askModel(
                promptBuilder.buildAlertPrompt(logs),
                AlertAnalysisResponse.class,
                "alert"
        );
    }

    // INCIDENT TIMELINE (timeline, severity, risk)
    public IncidentTimelineResponse generateIncidentTimeline(String logs) {
        return askModel(
                promptBuilder.buildTimelinePrompt(logs),
                IncidentTimelineResponse.class,
                "timeline"
        );
    }

    public IncidentSummaryResponse summarizeIncident(String logs) {
        return askModel(
                promptBuilder.buildIncidentSummaryPrompt(logs),
                IncidentSummaryResponse.class,
                "incident summary"
        );
    }

    public AnomalyDetectionResponse detectAnomaly(String logs) {
        return askModel(
                promptBuilder.buildAnomalyPrompt(logs),
                AnomalyDetectionResponse.class,
                "anomaly"
        );
    }

    public FixRecommendationResponse recommendFix(String logs) {
        return askModel(
                promptBuilder.buildFixPrompt(logs),
                FixRecommendationResponse.class,
                "recommend fix"
        );
    }

    public DevOpsChatResponse devOpsChat(String question) {
        return askModel(
                promptBuilder.buildDevOpsChatPrompt(question),
                DevOpsChatResponse.class,
                "devops chat"
        );
    }

    private boolean isValidJson(String str) {
        try {
            mapper.readTree(str); // Try to parse the string into a tree
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    // Helper method to remove Markdown formatting
    private String cleanJsonResponse(String response) {
        // Remove Markdown block start (```json) and end (```)
        return response.replaceAll("```json|```", "").trim();
    }

    public String analyzeLogsNarrative(String logs) {
        return client.ask(promptBuilder.buildMultiLevelLogPrompt(logs));
    }
}
