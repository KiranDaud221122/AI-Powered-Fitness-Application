package com.example.aiservice.service;

import com.example.aiservice.models.Recommendations;
import com.example.aiservice.repository.RecommendationRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;

    public List<Recommendations> getUserRecommendation(String userId) {
        return recommendationRepository.findByUserId(userId);
    }

    public Recommendations getActivityRecomendation(String activityId) {
        return recommendationRepository.findByActivityId(activityId).orElseThrow(()->new RuntimeException("No Recommendation found for this activity:{} "+ activityId));
    }
}
