package com.medai.dto.response;

import com.medai.entity.RiskFinding;

import java.util.List;

public class DecisionSupportResponse {

    private Long visitId;
    private Long patientId;

    private String decisionLevel;   // OK / WARN / BLOCK
    private String decisionSummary; // 一句话总结

    private List<RiskFinding> findings;

    private String traceId;

    private String aiSuggestion;
    private Long aiSuggestionId;


    public DecisionSupportResponse() {}

    public DecisionSupportResponse(Long visitId, Long patientId,
                                   String decisionLevel, String decisionSummary,
                                   List<RiskFinding> findings, String traceId) {
        this.visitId = visitId;
        this.patientId = patientId;
        this.decisionLevel = decisionLevel;
        this.decisionSummary = decisionSummary;
        this.findings = findings;
        this.traceId = traceId;
    }

    public Long getVisitId() { return visitId; }
    public Long getPatientId() { return patientId; }
    public String getDecisionLevel() { return decisionLevel; }
    public String getDecisionSummary() { return decisionSummary; }
    public List<RiskFinding> getFindings() { return findings; }
    public String getTraceId() { return traceId; }

    public String getAiSuggestion() { return aiSuggestion; }
    public Long getAiSuggestionId() { return aiSuggestionId; }



    public void setVisitId(Long visitId) { this.visitId = visitId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public void setDecisionLevel(String decisionLevel) { this.decisionLevel = decisionLevel; }
    public void setDecisionSummary(String decisionSummary) { this.decisionSummary = decisionSummary; }
    public void setFindings(List<RiskFinding> findings) { this.findings = findings; }
    public void setTraceId(String traceId) { this.traceId = traceId; }

    public void setAiSuggestion(String aiSuggestion) { this.aiSuggestion = aiSuggestion; }
    public void setAiSuggestionId(Long aiSuggestionId) { this.aiSuggestionId = aiSuggestionId; }
}
