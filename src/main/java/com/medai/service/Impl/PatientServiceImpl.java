package com.medai.service.Impl;

import com.medai.dto.request.CreatePatientRequest;
import com.medai.dto.response.PatientResponse;
import com.medai.entity.Patient;
import com.medai.repository.PatientRepository;
import com.medai.service.AuditService;
import com.medai.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final AuditService auditService;

    public PatientServiceImpl(PatientRepository patientRepository, AuditService auditService) {
        this.patientRepository = patientRepository;
        this.auditService = auditService;
    }

    @Override
    public PatientResponse create(CreatePatientRequest req, String traceId) {
        Patient p = new Patient();
        p.setFullName(req.getFullName());
        p.setAge(req.getAge());
        p.setGender(req.getGender());

        Patient saved = patientRepository.save(p);

        auditService.log("PATIENT_CREATE", "system", "PATIENT",
                String.valueOf(saved.getId()), traceId, "created patient");

        return toResponse(saved);
    }

    @Override
    public PatientResponse getById(Long id, String traceId) {
        Patient p = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("patient not found: " + id));

        auditService.log("PATIENT_VIEW", "system", "PATIENT",
                String.valueOf(id), traceId, "view patient");

        return toResponse(p);
    }

    @Override
    public List<PatientResponse> list(String traceId) {
        List<Patient> list = patientRepository.findAll();
        List<PatientResponse> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            res.add(toResponse(list.get(i)));
        }

        auditService.log("PATIENT_LIST", "system", "PATIENT",
                "-", traceId, "list patients size=" + res.size());

        return res;
    }

    private PatientResponse toResponse(Patient p) {
        return new PatientResponse(p.getId(), p.getFullName(), p.getAge(), p.getGender());
    }
}