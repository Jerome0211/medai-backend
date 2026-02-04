package com.medai.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "ai_suggestions")
public class AISuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long visitId;

    @Column(nullable = false)
    private Long patientId;

    private String model; // gpt-4o-mini / etc. MVP先写 "stub"

    @Column(columnDefinition = "TEXT")
    private String prompt;

    @Column(columnDefinition = "TEXT")
    private String output;

    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

    public Long getId() { return id; }
    public Long getVisitId() { return visitId; }
    public Long getPatientId() { return patientId; }
    public String getModel() { return model; }
    public String getPrompt() { return prompt; }
    public String getOutput() { return output; }
    public Instant getCreatedAt() { return createdAt; }

    public void setVisitId(Long visitId) { this.visitId = visitId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public void setModel(String model) { this.model = model; }
    public void setPrompt(String prompt) { this.prompt = prompt; }
    public void setOutput(String output) { this.output = output; }
}
