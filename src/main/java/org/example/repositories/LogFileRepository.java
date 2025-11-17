package org.example.repositories;

import org.example.entities.LogFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogFileRepository extends JpaRepository<LogFile, Long> {
}
