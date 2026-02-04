package com.medai.service;

import com.medai.entity.Patient;
import com.medai.entity.RiskFinding;
import com.medai.entity.Visit;

import java.util.List;

public interface AIService {
    String generateSuggestion(Patient patient, Visit visit, List<RiskFinding> findings);
}
