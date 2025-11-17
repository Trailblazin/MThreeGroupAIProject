package org.example.services

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.example.repositories.{LogFileRepository, LogRepository}
import org.example.entities.Log
import org.example.exceptions.ApiException
import org.example.entities.LogFile

import java.time.LocalDateTime

@Service
class LogService @Autowired()(repo: LogRepository, fileRepo: LogFileRepository) {

  def getAll() = repo.findAll()

  def getById(id: Long) =
    repo.findById(id).orElseThrow(() => new ApiException("Log not found"))

  def create(logPath: String): LogFile = {
    val logFile = new LogFile()
    logFile.setFilePath(logPath)
    //TODO: should be get latest ID
    logFile.setId(1)
    logFile.setUploadedTime(LocalDateTime.now)
    fileRepo.save(logFile)
  }

  def update(id: Long, log: Log): Log = {
    val existing = getById(id)
    existing.setName(log.getName)
    repo.save(existing)
  }

  def deleteById(id: Long): Unit =
    repo.deleteById(id)

  def deleteAll(): Unit =
    repo.deleteAll()
}
