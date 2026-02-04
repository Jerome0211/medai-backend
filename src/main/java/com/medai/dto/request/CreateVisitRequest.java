package com.medai.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateVisitRequest {

    @NotNull
    private Long patientId;

    @NotBlank
    private String chiefComplaint;

    public Long getPatientId() { return patientId; }
    public String getChiefComplaint() { return chiefComplaint; }

    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public void setChiefComplaint(String chiefComplaint) { this.chiefComplaint = chiefComplaint; }
}
