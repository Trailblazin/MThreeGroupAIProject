package org.example.entities;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class LogFile {
    private Long id;
    private String filePath;
    private LocalDateTime uploadedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getUploadedTime() {
        return uploadedTime;
    }

    public void setUploadedTime(LocalDateTime uploadedTime) {
        this.uploadedTime = uploadedTime;
    }
}
