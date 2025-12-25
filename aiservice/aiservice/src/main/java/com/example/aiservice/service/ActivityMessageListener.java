package com.example.aiservice.service;

import com.example.aiservice.models.Activity;
import com.example.aiservice.models.Recommendations;
import com.example.aiservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final ActivityAIService activityAIService;
    private final RecommendationRepository recommendationRepository;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "activity-processor-group")
    public void processActivity(Activity activity){
        log.info("Received activity for processing :{}",activity.getUserId());
        Recommendations recommendations= activityAIService.generateRecommendation(activity);
        recommendationRepository.save(recommendations);

    }

}
