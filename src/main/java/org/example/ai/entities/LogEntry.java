package org.example.ai.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logs;

    private LocalDateTime timestamp;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLogs() { return logs; }
    public void setLogs(String logs) { this.logs = logs; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
