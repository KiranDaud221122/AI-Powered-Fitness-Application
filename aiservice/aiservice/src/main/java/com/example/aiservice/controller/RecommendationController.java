package com.example.aiservice.controller;

import com.example.aiservice.models.Recommendations;
import com.example.aiservice.service.RecommendationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/recommendations")
@RestController
@AllArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    //Get User Recommendations
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendations>> getUserRecommendations (@PathVariable String userId){
        return ResponseEntity.ok(recommendationService.getUserRecommendation(userId));
    }

    //Get Activity Recommendations
    @GetMapping("/activity/{activityId}")
    public ResponseEntity <Recommendations> getActivityRecommendations(@PathVariable String activityId){
        return ResponseEntity.ok(recommendationService.getActivityRecomendation(activityId));

    }


}
