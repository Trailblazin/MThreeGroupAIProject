package org.example.ai.entities;

public class AlertAnalysisResponse {

    private String severity;
    private String classification;
    private String groupedAlerts;
    private String noiseReduction;
    private String rootCause;
    private String recommendedAction;

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getGroupedAlerts() {
        return groupedAlerts;
    }

    public void setGroupedAlerts(String groupedAlerts) {
        this.groupedAlerts = groupedAlerts;
    }

    public String getNoiseReduction() {
        return noiseReduction;
    }

    public void setNoiseReduction(String noiseReduction) {
        this.noiseReduction = noiseReduction;
    }

    public String getRootCause() {
        return rootCause;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }

    public String getRecommendedAction() {
        return recommendedAction;
    }

    public void setRecommendedAction(String recommendedAction) {
        this.recommendedAction = recommendedAction;
    }
}
