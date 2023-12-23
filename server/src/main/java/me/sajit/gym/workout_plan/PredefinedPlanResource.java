package me.sajit.gym.workout_plan;

import lombok.RequiredArgsConstructor;
import me.sajit.gym.shared.Response;
import me.sajit.gym.workout_plan.dto.CreateWorkoutPlan;
import me.sajit.gym.workout_plan.dto.GetPredefinedPlan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("predefined-plan")
@RequiredArgsConstructor
public class PredefinedPlanResource {

    private final PredefinedPlanService predefinedPlanService;

    @GetMapping("")
    ResponseEntity<Response> list(@ModelAttribute GetPredefinedPlan filter) {
        return ResponseEntity.ok(Response.builder()
                .data(Map.of("predefined_plans", predefinedPlanService.getAllPredefinedService(filter)))
                .message("Successfully fetched predefined plans")
                .status(HttpStatus.OK)
                .build());
    }

    @PostMapping("")
    ResponseEntity<Response> createWorkoutPlan(@RequestBody CreateWorkoutPlan createWorkoutPlan) {
        return ResponseEntity.ok(
                Response.builder()
                        .data(Map.of("predefined_plan", predefinedPlanService.createWorkoutPlan(createWorkoutPlan)))
                        .message("Successfully Created predefined workout plan")
                        .status(HttpStatus.OK)
                        .build());
    }

}
