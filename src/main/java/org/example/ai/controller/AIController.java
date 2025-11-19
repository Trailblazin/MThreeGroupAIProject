package org.example.ai.controller;

import org.example.ai.entities.AnalyzeLogResponse;
import org.example.ai.entities.AlertAnalysisResponse;
import org.example.ai.entities.IncidentTimelineResponse;
import org.example.ai.services.AIService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    // BASIC CHAT
    @PostMapping(value = "/chat", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String chat(@RequestBody String message) {
        return aiService.chat(message);
    }

    // MAIN LOG ANALYSIS
    @PostMapping(value = "/analyze", consumes = MediaType.TEXT_PLAIN_VALUE)
    public AnalyzeLogResponse analyze(@RequestBody String logs) {
        return aiService.analyzeLogs(logs);
    }

    // MAIN LOG ANALYSIS - via file upload (multipart)
    @PostMapping(value = "/analyze", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AnalyzeLogResponse analyzeFile(
            @RequestParam("file") MultipartFile file) throws IOException {

        // Store the file and get the file path
        String filePath = aiService.storeFile(file);

        // Use the file path or the file content to analyze
        return aiService.analyzeFileLogs(filePath);
    }

    // MULTI-LEVEL LOG ANALYSIS (textual response)
    @PostMapping(value = "/analyze-detailed", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String analyzeDetailed(@RequestBody String logs) {
        return aiService.analyzeLogsNarrative(logs);
    }

    @PostMapping(value = "/analyze/raw", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String analyzeRaw(@RequestBody String logs) {
        return aiService.analyzeLogsNarrative(logs);
    }

    // ALERT CLASSIFICATION + GROUPING + RCA
    @PostMapping(value = "/alerts", consumes = MediaType.TEXT_PLAIN_VALUE)
    public AlertAnalysisResponse alerts(@RequestBody String logs) {
        return aiService.analyzeAlerts(logs);
    }

    // INCIDENT TIMELINE GENERATOR
    @PostMapping(value = "/timeline", consumes = MediaType.TEXT_PLAIN_VALUE)
    public IncidentTimelineResponse timeline(@RequestBody String logs) {
        return aiService.generateIncidentTimeline(logs);
    }
}
