package com.fitness.activityservice.controller;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activity")
@AllArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> getActivityById (@PathVariable String activityId){
        return ResponseEntity.ok(activityService.getActivityById(activityId));
    }


    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request){
        return ResponseEntity.ok(activityService.trackActivity(request));
    }


   @DeleteMapping
    public ResponseEntity<Void> deleteActivity(@RequestParam String activityId){
        activityService.deleteActivity(activityId);
        return ResponseEntity.noContent().build();
    }
}