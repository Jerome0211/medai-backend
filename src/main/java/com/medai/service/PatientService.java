package com.medai.service;

import com.medai.dto.request.CreatePatientRequest;
import com.medai.dto.response.PatientResponse;

import java.util.List;

public interface PatientService {
    PatientResponse create(CreatePatientRequest req, String traceId);
    PatientResponse getById(Long id, String traceId);
    List<PatientResponse> list(String traceId);
}
