package com.medai.service.Impl;

import com.medai.entity.AuditLog;
import com.medai.repository.AuditLogRepository;
import com.medai.service.AuditService;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {

    private final AuditLogRepository auditLogRepository;

    public AuditServiceImpl(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Override
    public void log(String eventType, String actor, String subjectType, String subjectId, String traceId, String message) {
        AuditLog log = new AuditLog();
        log.setEventType(eventType);
        log.setActor(actor);
        log.setSubjectType(subjectType);
        log.setSubjectId(subjectId);
        log.setTraceId(traceId);
        log.setMessage(message);
        auditLogRepository.save(log);
    }
}