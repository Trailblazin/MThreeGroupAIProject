package org.example.ai.controller;

import org.example.ai.entities.LogEntry;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.example.ai.services.LogService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public LogEntry create(@RequestBody String logs) {
        return logService.save(logs);
    }

    @PostMapping(value = "/upload", consumes = MediaType.TEXT_PLAIN_VALUE)
    public LogEntry upload(@RequestBody String logs) {
        return logService.save(logs);
    }

    @PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public LogEntry uploadFile(@RequestPart("file") MultipartFile file) throws IOException {
        String logs = new String(file.getBytes(), StandardCharsets.UTF_8);
        return logService.save(logs);
    }

    @PostMapping(value = "/chunks/{sessionId}", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> uploadChunk(@PathVariable String sessionId, @RequestBody String chunk) {
        logService.appendChunk(sessionId, chunk);
        return Map.of(
                "sessionId", sessionId,
                "status", "chunk buffered",
                "chunkLength", chunk.length()
        );
    }

    @PostMapping(value = "/chunks/{sessionId}/complete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LogEntry> completeChunks(@PathVariable String sessionId,
                                                   @RequestBody(required = false) String finalChunk) {
        if (finalChunk != null && !finalChunk.isEmpty()) {
            logService.appendChunk(sessionId, finalChunk);
        }
        LogEntry entry = logService.completeChunk(sessionId);
        return ResponseEntity.ok(entry);
    }

    @GetMapping
    public List<LogEntry> getAll() {
        return logService.findAll();
    }

    @GetMapping("/{id}")
    public LogEntry getOne(@PathVariable Long id) {
        return logService.findById(id);
    }
}
