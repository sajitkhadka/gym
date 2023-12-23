package me.sajit.gym.workout_plan;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.sajit.gym.workout_category.WorkoutCategory;
import me.sajit.gym.workout_category.WorkoutCategoryService;
import me.sajit.gym.workout_plan.domain.PredefinedPlan;
import me.sajit.gym.workout_plan.domain.PredefinedPlanCategory;
import me.sajit.gym.workout_plan.domain.PredefinedPlanSchedule;
import me.sajit.gym.workout_plan.dto.CreateWorkoutPlan;
import me.sajit.gym.workout_plan.dto.GetPredefinedPlan;
import me.sajit.gym.workout_plan.repository.PredefinedPlanRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class PredefinedPlanServiceImpl implements  PredefinedPlanService{

    private final PredefinedPlanRepository predefinedPlanRepo;
    private final WorkoutCategoryService workoutCategoryService;
    @Override
    public List<PredefinedPlan> getAllPredefinedService(GetPredefinedPlan filter) {
        if (filter.getWorkoutPerCycle() != null) {
            return predefinedPlanRepo.findByWorkoutPerCycle(filter.getWorkoutPerCycle());
        } else {
            return predefinedPlanRepo.findAll();
        }
    }

    @Override
    public PredefinedPlan createWorkoutPlan(CreateWorkoutPlan createWorkoutDto) {
        PredefinedPlan predefinedPlan = createWorkoutDto.getPredefinedPlan();
        List<PredefinedPlanSchedule> predefinedPlanSchedules = new ArrayList<>();
        for(List<Integer> workouts: createWorkoutDto.getWorkoutSchedules()){
            PredefinedPlanSchedule schedule = PredefinedPlanSchedule
                    .builder()
                    .predefinedPlan(predefinedPlan)
                    .build();
            StringBuilder scheduleName = new StringBuilder();
            List<PredefinedPlanCategory> predefinedPlanCategories = new ArrayList<>();
            for(int category: workouts){
                WorkoutCategory workoutCategory = workoutCategoryService.getWorkoutCategoryById(category);
                if(!scheduleName.toString().equals("")){
                    scheduleName.append("/");
                }
                scheduleName.append(workoutCategory.getCategory());
                PredefinedPlanCategory predefinedPlanCategory = PredefinedPlanCategory
                        .builder()
                        .predefinedPlanSchedule(schedule)
                        .workoutCategory(workoutCategory)
                        .build();
                predefinedPlanCategories.add(predefinedPlanCategory);
            }
            schedule.setScheduleName(scheduleName.toString());
            schedule.setPredefinedPlanCategories(predefinedPlanCategories);
            predefinedPlanSchedules.add(schedule);
        }
        predefinedPlan.setPredefinedPlanSchedules(predefinedPlanSchedules);
        predefinedPlanRepo.save(predefinedPlan);
        return predefinedPlan;
    }
}
