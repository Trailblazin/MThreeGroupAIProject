package org.example.ai.entities;

import java.util.List;

public class AnalyzeLogResponse {

    private String summary;
    private String rootCause;
    private String impact;
    private String actionPlan;
    private List<LogInsight> insights;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRootCause() {
        return rootCause;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getActionPlan() {
        return actionPlan;
    }

    public void setActionPlan(String actionPlan) {
        this.actionPlan = actionPlan;
    }

    public List<LogInsight> getInsights() {
        return insights;
    }

    public void setInsights(List<LogInsight> insights) {
        this.insights = insights;
    }
}
