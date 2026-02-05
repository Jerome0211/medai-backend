package com.medai.service.Impl;

import com.medai.dto.request.CreateVisitRequest;
import com.medai.dto.response.DecisionSupportResponse;
import com.medai.entity.AISuggestion;
import com.medai.entity.Patient;
import com.medai.entity.RiskFinding;
import com.medai.entity.Visit;
import com.medai.repository.AISuggestionRepository;
import com.medai.repository.PatientRepository;
import com.medai.repository.VisitRepository;
import com.medai.service.AIService;
import com.medai.service.AuditService;
import com.medai.service.RiskEngineService;
import com.medai.service.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;
    private final RiskEngineService riskEngineService;
    private final AuditService auditService;
    private final AIService aiService;
    private final AISuggestionRepository aiSuggestionRepository;

    public VisitServiceImpl(VisitRepository visitRepository,
                            PatientRepository patientRepository,
                            RiskEngineService riskEngineService,
                            AuditService auditService,
                            AIService aiService,
                            AISuggestionRepository aiSuggestionRepository) {
        this.visitRepository = visitRepository;
        this.patientRepository = patientRepository;
        this.riskEngineService = riskEngineService;
        this.auditService = auditService;
        this.aiService = aiService;
        this.aiSuggestionRepository = aiSuggestionRepository;
    }

    @Override
    public DecisionSupportResponse create(CreateVisitRequest req, String traceId) {
        // 1) 取 patient
        Patient patient = patientRepository.findById(req.getPatientId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "patient not found: " + req.getPatientId()
                ));

        // 2) 创建 visit
        Visit v = new Visit();
        v.setPatientId(req.getPatientId());
        v.setChiefComplaint(req.getChiefComplaint());

        Visit saved = visitRepository.save(v);

        auditService.log("VISIT_CREATE", "system", "VISIT",
                String.valueOf(saved.getId()), traceId, "created visit");

        // 3) 跑规则
        List<RiskFinding> findings = riskEngineService.evaluate(patient, saved);

        auditService.log("RISK_EVALUATE", "system", "VISIT",
                String.valueOf(saved.getId()), traceId, "risk findings size=" + findings.size());

        // 4) AI 建议（MVP 先 stub）
        String prompt = "patientAge=" + patient.getAge()
                + ", complaint=" + saved.getChiefComplaint()
                + ", findings=" + findings.size();

        String aiText = aiService.generateSuggestion(patient, saved, findings);

        // 5) AI 输出落库
        AISuggestion sug = new AISuggestion();
        sug.setVisitId(saved.getId());
        sug.setPatientId(saved.getPatientId());
        sug.setModel("stub");
        sug.setPrompt(prompt);
        sug.setOutput(aiText);

        AISuggestion savedSug = aiSuggestionRepository.save(sug);

        auditService.log("AI_SUGGESTION_CREATE", "system", "VISIT",
                String.valueOf(saved.getId()), traceId, "aiSuggestionId=" + savedSug.getId());

        // 6) 决策等级 + 总结（MVP 版）
        String level = decideLevel(findings);
        String summary = decideSummary(level, findings);

        // 7) 组装返回（MVP 版）
        DecisionSupportResponse resp = new DecisionSupportResponse();
        resp.setVisitId(saved.getId());
        resp.setPatientId(saved.getPatientId());
        resp.setDecisionLevel(level);
        resp.setDecisionSummary(summary);
        resp.setFindings(findings);
        resp.setTraceId(traceId);

        resp.setAiSuggestion(aiText);
        resp.setAiSuggestionId(savedSug.getId());

        return resp;
    }

    private String decideLevel(List<RiskFinding> findings) {
        // MVP：BLOCK > WARN > OK
        for (int i = 0; i < findings.size(); i++) {
            if ("BLOCK".equalsIgnoreCase(findings.get(i).getLevel())) {
                return "BLOCK";
            }
        }
        for (int i = 0; i < findings.size(); i++) {
            if ("WARN".equalsIgnoreCase(findings.get(i).getLevel())) {
                return "WARN";
            }
        }
        return "OK";
    }

    private String decideSummary(String level, List<RiskFinding> findings) {
        if ("OK".equalsIgnoreCase(level)) {
            return "No significant risk signals detected by rules.";
        }
        if ("WARN".equalsIgnoreCase(level)) {
            return "Potential risk detected. Review recommended.";
        }
        return "High risk detected. Consider escalation.";
    }
}
