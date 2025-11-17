package org.example.ai.repositories;

import org.example.ai.entities.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntry, Long> {
}
