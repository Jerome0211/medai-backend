package com.medai.dto.response;

import java.time.Instant;

public class VisitResponse {

    private Long id;
    private Long patientId;
    private String chiefComplaint;
    private Instant visitTime;

    public VisitResponse() {}

    public VisitResponse(Long id, Long patientId, String chiefComplaint, Instant visitTime) {
        this.id = id;
        this.patientId = patientId;
        this.chiefComplaint = chiefComplaint;
        this.visitTime = visitTime;
    }

    public Long getId() { return id; }
    public Long getPatientId() { return patientId; }
    public String getChiefComplaint() { return chiefComplaint; }
    public Instant getVisitTime() { return visitTime; }

    public void setId(Long id) { this.id = id; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public void setChiefComplaint(String chiefComplaint) { this.chiefComplaint = chiefComplaint; }
    public void setVisitTime(Instant visitTime) { this.visitTime = visitTime; }
}
