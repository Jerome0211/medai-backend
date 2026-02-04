package com.medai.controller;


//负责“病人这个人本身”
import com.medai.dto.request.CreatePatientRequest;
import com.medai.dto.response.PatientResponse;
import com.medai.service.PatientService;
import com.medai.util.TraceIdUtil;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public PatientResponse create(@Valid @RequestBody CreatePatientRequest req) {
        String traceId = TraceIdUtil.newTraceId();
        return patientService.create(req, traceId);
    }

    @GetMapping("/{id}")
    public PatientResponse get(@PathVariable Long id) {
        String traceId = TraceIdUtil.newTraceId();
        return patientService.getById(id, traceId);
    }

    @GetMapping
    public List<PatientResponse> list() {
        String traceId = TraceIdUtil.newTraceId();
        return patientService.list(traceId);
    }
}
