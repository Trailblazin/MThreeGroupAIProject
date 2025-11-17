package org.example.ai.controller;

import org.example.ai.entities.AnomalyDetectionResponse;
import org.example.ai.entities.DevOpsChatResponse;
import org.example.ai.entities.FixRecommendationResponse;
import org.example.ai.entities.IncidentSummaryResponse;
import org.example.ai.services.AIService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AiAnalysisController {

    private final AIService aiService;

    public AiAnalysisController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping(value = "/summarize-incident", consumes = MediaType.TEXT_PLAIN_VALUE)
    public IncidentSummaryResponse summarize(@RequestBody String logs) {
        return aiService.summarizeIncident(logs);
    }

    @PostMapping(value = "/detect-anomaly", consumes = MediaType.TEXT_PLAIN_VALUE)
    public AnomalyDetectionResponse detect(@RequestBody String logs) {
        return aiService.detectAnomaly(logs);
    }

    @PostMapping(value = "/recommend-fix", consumes = MediaType.TEXT_PLAIN_VALUE)
    public FixRecommendationResponse recommend(@RequestBody String logs) {
        return aiService.recommendFix(logs);
    }

    @PostMapping(value = "/devops-chat", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DevOpsChatResponse chat(@RequestBody String question) {
        return aiService.devOpsChat(question);
    }
}
