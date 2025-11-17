package org.example.services

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.beans.factory.annotation.Autowired
import org.example.repositories.LogRepository
import org.example.entities.{AnalysisType, Log}
import org.example.utils.PromptBuilder.PromptBuilder


@Service
class AIService @Autowired()(logRepo: LogRepository) {

  private val client = WebClient.builder().build()
  private val prompt = new PromptBuilder
  private val HFModel = ""

  //analyse/summarise a log
  def doAnalysis(log: Log): String = {
    "Todo"

    /*
    val body = Map("inputs" -> prompt.buildAnalysisPrompt(log.content, log.logType))
client.post().uri(s"#INSERT_URL#/${HFModel}")
.bodyValue(body)
.retrieve()
.bodyToMono(classOf[String])
.block()
     */

    //val analysisType = AnalysisType.SUMMARY.toString()
  }

  //Generate incident summary
  def buildIncidentSummary(log: Log): String = {
    "Todo"
    val body = Map("inputs" -> prompt.buildSummaryPrompt(log.content))
    client.post().uri(s"#INSERT_URL#/${HFModel}")
      .bodyValue(body)
      .retrieve()
      .bodyToMono(classOf[String])
      .block()

    //val analysisType = AnalysisType.INCIDENT_SUMMARY.toString()
  }

  //categorise a log by log type
  def getLogType(log: Log): String = {
    "Todo"
    //val analysisType = AnalysisType.CATEGORIZE.toString()
  }

  //Detect Anomalies
  def buildAnomalySummary(log: Log): String = {
    "Todo"
    val body = Map("inputs" -> prompt.buildAnomalyPrompt(log.content))
    client.post().uri(s"#INSERT_URL#/${HFModel}")
      .bodyValue(body)
      .retrieve()
      .bodyToMono(classOf[String])
      .block()
    //val analysisType = AnalysisType.ANOMALY_SUMMARY.toString()
  }

  //Recommend fix
  def recommendFix(log: Log): String = {
    "Todo"
    //val analysisType = AnalysisType.FIX.toString()

  }

  //Start chat
  def statChat(log: Log) = {

  }
}
