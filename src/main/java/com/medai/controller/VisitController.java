package com.medai.controller;


//负责“一次就诊事件”


import com.medai.dto.request.CreateVisitRequest;
import com.medai.dto.response.DecisionSupportResponse;
import com.medai.service.VisitService;
import com.medai.util.TraceIdUtil;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/visits")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping
    public DecisionSupportResponse create(@Valid @RequestBody CreateVisitRequest req) {
        String traceId = TraceIdUtil.newTraceId();
        return visitService.create(req, traceId);
    }
}
