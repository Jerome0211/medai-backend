package com.medai.service;

import com.medai.entity.Patient;
import com.medai.entity.RiskFinding;
import com.medai.entity.Visit;

import java.util.List;

public interface RiskEngineService {
    List<RiskFinding> evaluate(Patient patient, Visit visit);
}
