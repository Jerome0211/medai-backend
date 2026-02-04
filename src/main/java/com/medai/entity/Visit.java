package com.medai.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // MVP：先用 patientId 字段（后面再改成 @ManyToOne）
    @Column(nullable = false)
    private Long patientId;

    @Column(nullable = false)
    private String chiefComplaint;   // 主诉

    private Instant visitTime;

    @PrePersist
    public void prePersist() {
        if (visitTime == null) {
            visitTime = Instant.now();
        }
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
