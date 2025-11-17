package org.example.utils.PromptBuilder

import org.example.entities.{Log, LogType}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PromptBuilder @Autowired() {
  def buildAnalysisPrompt(log: Log, logType: LogType): String =
    s"""
       |You are an AI SRE assistant.
       |Analyze the following log and produce:
       |1. Root Cause
       |2. Evidence
       |3. Suggested Fix
       |
       |LOGS:
       |${log.getLogLines.toString}
       |""".stripMargin


  def buildSummaryPrompt(log: Log): String =
    s"""
       |Summarize the incident in 5–10 lines.
       |
       |${log.getLogLines.toString}
       |""".stripMargin


  def buildAnomalyPrompt(log: Log): String =
    s"""
       |Detect anomalies in the logs and return a structured response.
       |
       |${log.getLogLines.toString}
       |""".stripMargin
}
