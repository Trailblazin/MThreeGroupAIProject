package org.example.services

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.example.repositories.LogRepository
import org.example.entities.Log
import org.example.exceptions.ApiException

@Service
class LogService @Autowired()(repo: LogRepository) {

  def getAll() = repo.findAll()

  def getById(id: Long) =
    repo.findById(id).orElseThrow(() => new ApiException("Log not found"))

  def create(log: Log): Log = {
    repo.save(log)
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
