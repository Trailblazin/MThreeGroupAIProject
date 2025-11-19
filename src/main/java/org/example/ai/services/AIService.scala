package org.example.ai.services


import com.fasterxml.jackson.databind.ObjectMapper
import org.example.ai.client.GroqClient
import org.example.ai.entities._
import org.example.ai.repositories.LogRepository
import org.example.ai.utils.PromptBuilder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

import java.io.{File, IOException}
import java.nio.file.{Files, Paths, StandardCopyOption}
import java.time.LocalDateTime


@Service
class AIService(private val client: GroqClient, private val promptBuilder: PromptBuilder, private val logService: LogService){

  private val mapper = new ObjectMapper();


  private def askModel[T](prompt: String, clazz: Class[T], operation: String) = try {
    val result = cleanJsonResponse(client.ask(prompt))
    // Check if the result is valid JSON

    if (!isValidJson(result)) throw new RuntimeException("Model response is not valid JSON: " + result)
    mapper.readValue(result, clazz)
  } catch {
    case e: Exception =>
      System.err.println("Error occurred during " + operation + " with prompt: " + prompt)
      throw new RuntimeException("Failed to parse " + operation + " JSON", e)
  }

  // BASIC CHAT
  def chat(message: String): String = client.ask(message)

  // MAIN LOG ANALYSIS
  def analyzeLogs(logs: String): AnalyzeLogResponse = askModel(promptBuilder.buildAnalysisPrompt(logs), classOf[AnalyzeLogResponse], "analyzeLogs")

  // ALERT INTELLIGENCE (classification, grouping, RCA)
  def analyzeAlerts(logs: String): AlertAnalysisResponse = askModel(promptBuilder.buildAlertPrompt(logs), classOf[AlertAnalysisResponse], "alert")

  // INCIDENT TIMELINE (timeline, severity, risk)
  def generateIncidentTimeline(logs: String): IncidentTimelineResponse = askModel(promptBuilder.buildTimelinePrompt(logs), classOf[IncidentTimelineResponse], "timeline")

  def summarizeIncident(logs: String): IncidentSummaryResponse = askModel(promptBuilder.buildIncidentSummaryPrompt(logs), classOf[IncidentSummaryResponse], "incident summary")

  def detectAnomaly(logs: String): AnomalyDetectionResponse = askModel(promptBuilder.buildAnomalyPrompt(logs), classOf[AnomalyDetectionResponse], "anomaly")

  def recommendFix(logs: String): FixRecommendationResponse = askModel(promptBuilder.buildFixPrompt(logs), classOf[FixRecommendationResponse], "recommend fix")

  def devOpsChat(question: String): DevOpsChatResponse = askModel(promptBuilder.buildDevOpsChatPrompt(question), classOf[DevOpsChatResponse], "devops chat")

  private def isValidJson(str: String) = try {
    mapper.readTree(str) // Try to parse the string into a tree

    true
  } catch {
    case e: IOException =>
      false
  }

  // Helper method to remove Markdown formatting
  private def cleanJsonResponse(response: String) = {
    // Remove Markdown block start (```json) and end (```)
    response.replaceAll("```json|```", "").trim
  }

  def analyzeLogsNarrative(logs: String): String = client.ask(promptBuilder.buildMultiLevelLogPrompt(logs))

  //FileHandling Logic


  // Method to store the uploaded file and return its file path
  def storeFile(file: MultipartFile): String = {
    val uploadDir = "logs/uploads"
    val fileName = file.getOriginalFilename
    val filePath = Paths.get(uploadDir, fileName).toString

    // Save the file to the specified path
    Files.copy(file.getInputStream, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING)

    // Return the relative file path
    filePath
  }

  // Method to analyze logs from a file path
  def analyzeFile(filePath: String): AnalyzeLogResponse = {
    // Read file content and pass it to the AI model for analysis
    val logs = scala.io.Source.fromFile(filePath).mkString
    //Have to save before analysis
    analyzeLogs(logService.saveFromFile(logs, filePath))
  }

  // Main log analysis (same as before)
  def analyzeFileLogs(logs: String): AnalyzeLogResponse = {
    askModel(promptBuilder.buildAnalysisPrompt(logs), classOf[AnalyzeLogResponse], "analyzeLogs")
  }
}