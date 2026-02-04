package com.medai.service.Impl;

import com.medai.entity.Patient;
import com.medai.entity.RiskFinding;
import com.medai.entity.Visit;
import com.medai.service.RiskEngineService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RiskEngineServiceImpl implements RiskEngineService {

    @Override
    public List<RiskFinding> evaluate(Patient patient, Visit visit) {
        List<RiskFinding> res = new ArrayList<>();

        // 规则1：年龄>40 + 主诉包含 chest pain/胸闷 => WARN
        if (patient.getAge() != null && patient.getAge() > 40) {
            String cc = visit.getChiefComplaint();
            if (cc != null) {
                String lower = cc.toLowerCase();
                if (lower.contains("chest") || lower.contains("胸") || lower.contains("闷")) {
                    res.add(new RiskFinding(
                            "CV_RISK_CHEST_PAIN",
                            "WARN",
                            "Consider cardiovascular risk evaluation (ECG / cardiac enzymes)."
                    ));
                }
            }
        }

        return res;
    }
}
