package org.example.ai.services;

import org.example.ai.entities.LogEntry;
import org.example.ai.repositories.LogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LogService {

    private final LogRepository logRepository;
    private final Map<String, StringBuilder> chunkBuffers = new ConcurrentHashMap<>();

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public LogEntry save(String logs) {
        LogEntry entry = new LogEntry();
        entry.setLogs(logs);
        entry.setTimestamp(LocalDateTime.now());
        return logRepository.save(entry);
    }

    public void appendChunk(String sessionId, String chunk) {
        chunkBuffers.compute(sessionId, (id, builder) -> {
            if (builder == null) {
                builder = new StringBuilder();
            }
            builder.append(chunk);
            return builder;
        });
    }

    public LogEntry completeChunk(String sessionId) {
        StringBuilder buffer = chunkBuffers.remove(sessionId);
        if (buffer == null) {
            throw new RuntimeException("No chunk session found for id: " + sessionId);
        }
        return save(buffer.toString());
    }

    public List<LogEntry> findAll() {
        return logRepository.findAll();
    }

    public LogEntry findById(Long id) {
        return logRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log not found"));
    }
}
