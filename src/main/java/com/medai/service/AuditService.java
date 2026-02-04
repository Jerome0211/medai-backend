package com.medai.service;

public interface AuditService {
    void log(String eventType, String actor, String subjectType, String subjectId, String traceId, String message);
}
