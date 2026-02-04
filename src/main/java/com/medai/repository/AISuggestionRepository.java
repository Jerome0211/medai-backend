package com.medai.repository;

import com.medai.entity.AISuggestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AISuggestionRepository extends JpaRepository<AISuggestion, Long> {
    List<AISuggestion> findByVisitId(Long visitId);
}
