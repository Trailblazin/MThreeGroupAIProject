package org.example.ai.utils;

import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {

    public String buildAnalysisPrompt(String logs) {
        return "You are an expert DevOps/SRE log analysis engine. " +
                "Analyze these logs and respond ONLY in this JSON format:\n\n" +
                "{\n" +
                "  \"summary\": \"overall summary\",\n" +
                "  \"rootCause\": \"primary failure\",\n" +
                "  \"impact\": \"business/system impact\",\n" +
                "  \"actionPlan\": \"next remediation steps\",\n" +
                "  \"insights\": [\n" +
                "     {\n" +
                "       \"level\": \"ERROR | WARN | INFO\",\n" +
                "       \"timestamp\": \"if present\",\n" +
                "       \"component\": \"module or service\",\n" +
                "       \"message\": \"concise description\",\n" +
                "       \"recommendation\": \"action for this entry\"\n" +
                "     }\n" +
                "  ]\n" +
                "}\n\n" +
                "Ensure EVERY significant ERROR or WARN log results in its own entry in \"insights\". " +
                "If multiple issues exist, list them separately. LOGS:\n" + logs + "\n\n" +
                "Return JSON only, no extra text.";
    }

    public String buildAlertPrompt(String logs) {
        return "You classify alerts for SRE teams. " +
                "Return ONLY JSON:\n" +
                "{\n" +
                "  \"severity\": \"LOW | MEDIUM | HIGH | CRITICAL\",\n" +
                "  \"classification\": \"...\",\n" +
                "  \"groupedAlerts\": \"...\",\n" +
                "  \"noiseReduction\": \"...\",\n" +
                "  \"rootCause\": \"...\",\n" +
                "  \"recommendedAction\": \"...\"\n" +
                "}\n\n" +
                "ALERT LOGS:\n" + logs;
    }

    public String buildTimelinePrompt(String logs) {
        return "Create an incident timeline with severity and risk score. " +
                "Respond ONLY as strict JSON (no markdown/code fences):\n" +
                "{\n" +
                "  \"timeline\": \"Step-by-step timeline\",\n" +
                "  \"impact\": \"Business/system impact\",\n" +
                "  \"fix\": \"Fix that resolved it\",\n" +
                "  \"severity\": \"LOW | MEDIUM | HIGH | CRITICAL\",\n" +
                "  \"riskScore\": \"0-100\"\n" +
                "}\n\n" +
                "LOGS:\n" + logs;
    }

    public String buildIncidentSummaryPrompt(String logs) {
        return "Summarize the following logs in 5-10 concise lines. " +
                "Respond ONLY as JSON:\n" +
                "{\n" +
                "  \"summary\": \"5-10 line summary\",\n" +
                "  \"impact\": \"Impact statement\",\n" +
                "  \"priority\": \"P1 | P2 | P3 | P4\"\n" +
                "}\n\n" +
                "LOGS:\n" + logs;
    }

    public String buildAnomalyPrompt(String logs) {
        return "Detect anomalies within these logs. " +
                "Respond ONLY as strict JSON (no markdown/code fences):\n" +
                "{\n" +
                "  \"anomalyType\": \"Latency spike, CrashLoop, etc\",\n" +
                "  \"description\": \"What makes it anomalous\",\n" +
                "  \"riskLevel\": \"LOW | MEDIUM | HIGH | CRITICAL\",\n" +
                "  \"suggestedAction\": \"Immediate mitigation\"\n" +
                "}\n\n" +
                "LOGS:\n" + logs;
    }

    public String buildFixPrompt(String logs) {
        return "Recommend fixes for the following DevOps/infra issue. " +
                "Return ONLY JSON:\n" +
                "{\n" +
                "  \"probableCause\": \"...\",\n" +
                "  \"recommendedFix\": \"...\",\n" +
                "  \"rollbackPlan\": \"...\",\n" +
                "  \"confidence\": \"LOW | MEDIUM | HIGH\"\n" +
                "}\n\n" +
                "LOGS:\n" + logs;
    }

    public String buildDevOpsChatPrompt(String question) {
        return "You are a DevOps co-pilot that answers troubleshooting questions. " +
                "Respond ONLY as JSON:\n" +
                "{\n" +
                "  \"answer\": \"Detailed yet concise response\",\n" +
                "  \"nextSteps\": \"Actionable next steps\"\n" +
                "}\n\n" +
                "QUESTION OR LOGS:\n" + question;
    }

    public String buildMultiLevelLogPrompt(String logs) {
        return "You are a log intelligence assistant. Given raw log text, detect MULTIPLE events. " +
                "Produce plain text with the following sections (no JSON, no markdown):\n" +
                "Errors:\n- <timestamp> | <component> | <message>\n- ...\n" +
                "Warnings:\n- ...\n" +
                "Info:\n- ...\n" +
                "Observations:\n- Summarize correlations/anomalies\n" +
                "NextSteps:\n- Actionable remediation steps\n\n" +
                "If a section has no items, write 'None'. Use only ASCII characters. Logs:\n" + logs;
    }
}
