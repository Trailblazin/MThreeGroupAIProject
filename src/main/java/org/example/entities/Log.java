package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Log {

    private LogType logType;

    private List<String> logLines;

    public Log() {
    }
    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public List<String> getLogLines() {
        return logLines;
    }

    public void setLogLines(List<String> logLines) {
        this.logLines = logLines;
    }
}
