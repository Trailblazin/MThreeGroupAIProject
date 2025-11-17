package org.example.ai.entities;

public class IncidentTimelineResponse {
    private String timeline;
    private String impact;
    private String fix;
    private String severity;
    private String riskScore;

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getFix() {
        return fix;
    }

    public void setFix(String fix) {
        this.fix = fix;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(String riskScore) {
        this.riskScore = riskScore;
    }
}
