package me.sajit.gym.workout_plan;

import me.sajit.gym.workout_plan.domain.PredefinedPlan;
import me.sajit.gym.workout_plan.dto.CreateWorkoutPlan;
import me.sajit.gym.workout_plan.dto.GetPredefinedPlan;

import java.util.List;

public interface PredefinedPlanService {
    List<PredefinedPlan> getAllPredefinedService(GetPredefinedPlan filter);

    PredefinedPlan createWorkoutPlan(CreateWorkoutPlan createWorkoutDto);
}
