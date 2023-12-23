package me.sajit.gym.workout_plan.dto;

import lombok.Data;
import me.sajit.gym.workout_plan.domain.PredefinedPlan;

import java.util.List;

@Data
public class CreateWorkoutPlan {
    String name;
    int repeatCycle;
    int workoutPerCycle;
    String description;
    List<List<Integer>> workoutSchedules;
    public PredefinedPlan getPredefinedPlan(){
        return PredefinedPlan.builder()
                .description(description)
                .name(name)
                .workoutPerCycle(workoutPerCycle)
                .repeatCycle(repeatCycle)
                .build();
    }
}
