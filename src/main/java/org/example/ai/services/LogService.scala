package org.example.ai.services

import org.example.ai.entities.LogEntry
import org.example.ai.repositories.LogRepository
import org.springframework.stereotype.Service

import java.time.LocalDateTime
import java.util
import java.util.concurrent.ConcurrentHashMap

@Service
class LogService(private val logRepository: LogRepository) {
  private val chunkBuffers: util.Map[String, StringBuilder] = new ConcurrentHashMap[String, StringBuilder]

  def save(logs: String): LogEntry = {
    val entry: LogEntry = new LogEntry
    entry.setLogs(logs)
    entry.setLogFilePath("")
    entry.setTimestamp(LocalDateTime.now)
    logRepository.save(entry)
  }

  def saveFromFile(logs: String, filePath: String): String = {
    val entry: LogEntry = new LogEntry
    entry.setLogs("")
    entry.setLogFilePath(filePath)
    entry.setTimestamp(LocalDateTime.now)
    logRepository.save(entry)
    return logs
  }


  def appendChunk(sessionId: String, chunk: String): Unit = {
    chunkBuffers.compute(sessionId, (id: String, builder: StringBuilder) => {
      if (builder == null) new StringBuilder()
      else {
        builder.append(chunk)
        builder
      }
    })
  }

  def completeChunk(sessionId: String): LogEntry = {
    val buffer: StringBuilder = chunkBuffers.remove(sessionId)
    if (buffer == null) throw new RuntimeException("No chunk session found for id: " + sessionId)
    save(buffer.toString)
  }

  def findAll: util.List[LogEntry] = logRepository.findAll

  def findById(id: Long): LogEntry = logRepository.findById(id).orElseThrow(() => new RuntimeException("Log not found"))
}
