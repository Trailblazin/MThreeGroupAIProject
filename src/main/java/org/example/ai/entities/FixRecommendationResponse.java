package org.example.ai.entities;

public class FixRecommendationResponse {

    private String probableCause;
    private String recommendedFix;
    private String rollbackPlan;
    private String confidence;

    public String getProbableCause() {
        return probableCause;
    }

    public void setProbableCause(String probableCause) {
        this.probableCause = probableCause;
    }

    public String getRecommendedFix() {
        return recommendedFix;
    }

    public void setRecommendedFix(String recommendedFix) {
        this.recommendedFix = recommendedFix;
    }

    public String getRollbackPlan() {
        return rollbackPlan;
    }

    public void setRollbackPlan(String rollbackPlan) {
        this.rollbackPlan = rollbackPlan;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }
}
