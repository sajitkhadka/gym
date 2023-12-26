package me.sajit.gym.scripts.seeds;

import lombok.RequiredArgsConstructor;
import me.sajit.gym.workout_plan.PredefinedPlanService;
import me.sajit.gym.workout_plan.domain.PredefinedPlan;
import me.sajit.gym.workout_plan.domain.PredefinedPlanCategory;
import me.sajit.gym.workout_plan.domain.PredefinedPlanSchedule;
import me.sajit.gym.workout_plan.dto.CreateWorkoutPlan;
import me.sajit.gym.workout_plan.repository.PredefinedPlanRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PredefinedPlanSeeds implements ISeeder {

    private final CategorySeeds categorySeeds;
    private final PredefinedPlanService predefinedPlanService;
    private final PredefinedPlanRepository predefinedPlanRepository;

    @Override
    public void seedOld() {
        predefinedPlanService.createWorkoutPlan(CreateWorkoutPlan
                .builder()
                .repeatCycle(7)
                .workoutPerCycle(5)
                .name("Classic BodyBuilding 1# 5 days a week split")
                .description("This is classic bodybuilding 5 days a week split. It contains Push, Pull, Shoulder, Legs and full body or compound on separate days.")
                .workoutSchedules(List.of(List.of(categorySeeds.getUpperBodyPush().getId()),
                        List.of(categorySeeds.getUpperBodyPull().getId()),
                        List.of(categorySeeds.getShoulder().getId()),
                        List.of(categorySeeds.getLowerBody().getId()),
                        List.of(categorySeeds.getCompound().getId())
                ))
                .build());
    }

    @Override
    public void seedNew() {
        if(predefinedPlanRepository.findAll(PageRequest.of(0,1)).getContent().isEmpty()){
            seedOld();
        }
    }
}
