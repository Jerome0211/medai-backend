package com.medai.service;

import com.medai.dto.request.CreateVisitRequest;
import com.medai.dto.response.DecisionSupportResponse;

public interface VisitService {
    DecisionSupportResponse create(CreateVisitRequest req, String traceId);
}
