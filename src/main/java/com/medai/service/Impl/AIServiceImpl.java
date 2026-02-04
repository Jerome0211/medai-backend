package com.medai.service.Impl;

import com.medai.entity.Patient;
import com.medai.entity.RiskFinding;
import com.medai.entity.Visit;
import com.medai.service.AIService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIServiceImpl implements AIService {

    @Override
    public String generateSuggestion(Patient patient, Visit visit, List<RiskFinding> findings) {
        // MVP：假 AI（先把闭环跑通）
        String level = "OK";
        for (int i = 0; i < findings.size(); i++) {
            if ("WARN".equalsIgnoreCase(findings.get(i).getLevel())) {
                level = "WARN";
                break;
            }
        }

        if ("WARN".equals(level)) {
            return "AI Suggestion (stub): Patient has potential cardiovascular risk signals. " +
                    "Recommend ECG, cardiac enzymes, and consider ER referral if symptoms worsen.";
        }
        return "AI Suggestion (stub): No significant rule-based risk detected. Continue routine evaluation.";
    }
}
