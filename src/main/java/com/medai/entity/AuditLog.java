package com.medai.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType;
    private String actor;      // MVP 先用字符串，后面再接 User
    private String subjectType;
    private String subjectId;
    private String traceId;

    @Column(columnDefinition = "TEXT")
    private String message;

    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

    public Long getId() { return id; }
    public String getEventType() { return eventType; }
    public String getActor() { return actor; }
    public String getSubjectType() { return subjectType; }
    public String getSubjectId() { return subjectId; }
    public String getTraceId() { return traceId; }
    public String getMessage() { return message; }
    public Instant getCreatedAt() { return createdAt; }

    public void setEventType(String eventType) { this.eventType = eventType; }
    public void setActor(String actor) { this.actor = actor; }
    public void setSubjectType(String subjectType) { this.subjectType = subjectType; }
    public void setSubjectId(String subjectId) { this.subjectId = subjectId; }
    public void setTraceId(String traceId) { this.traceId = traceId; }
    public void setMessage(String message) { this.message = message; }
}
