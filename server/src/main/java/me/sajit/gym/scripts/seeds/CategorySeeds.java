package me.sajit.gym.scripts.seeds;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.sajit.gym.workout_category.WorkoutCategory;
import me.sajit.gym.workout_category.WorkoutCategoryRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Data
@Component
public class CategorySeeds {

    private final WorkoutCategoryRepository workoutCategoryRepository;
    private final ExerciseSeeds exerciseSeeds;
    private WorkoutCategory lowerBody;
    private WorkoutCategory upperBody;
    private WorkoutCategory fullBody;
    private WorkoutCategory upperBodyPush;
    private WorkoutCategory upperBodyPull;
    private WorkoutCategory arms;
    private WorkoutCategory compound;
    private WorkoutCategory cardio;
    private WorkoutCategory abs;
    private WorkoutCategory shoulder;
    private WorkoutCategory chest;
    private WorkoutCategory biceps;
    private WorkoutCategory triceps;
    private WorkoutCategory back;
    private WorkoutCategory core;
    private WorkoutCategory rest;

    @PostConstruct
    public void init() {
        lowerBody = workoutCategoryRepository.findOrCreateByCategory(WorkoutCategory.builder().category("Lower Body (Legs)").build());
        upperBody = workoutCategoryRepository.findOrCreateByCategory(WorkoutCategory.builder().category("Upper Body").build());
        fullBody = workoutCategoryRepository.findOrCreateByCategory(WorkoutCategory.builder().category("Full Body").build());
        upperBodyPush = workoutCategoryRepository.findOrCreateByCategory(WorkoutCategory.builder().category("Upper Body Push (Chest and Triceps)").build());
        upperBodyPull = workoutCategoryRepository.findOrCreateByCategory(WorkoutCategory.builder().category("Upper Body Pull (Back and Biceps)").build());
        arms = workoutCategoryRepository.findOrCreateByCategory(WorkoutCategory.builder().category("Arms").build());
        compound = workoutCategoryRepository.findOrCreateByCategory(WorkoutCategory.builder().category("Compound").build());
        cardio = workoutCategoryRepository.findOrCreateByCategory(WorkoutCategory.builder().category("Cardio").build());
        abs = workoutCategoryRepository.findOrCreateByCategory(WorkoutCategory.builder().category("Abs").build());
        shoulder = workoutCategoryRepository.findOrCreateByCategory(WorkoutCategory.builder().category("Shoulder").build());
        chest = workoutCategoryRepository.findOrCreateByCategory(WorkoutCategory.builder().category("Chest").build());
        biceps = workoutCategoryRepository.findOrCreateByCategory(WorkoutCategory.builder().category("Biceps").build());
        triceps = workoutCategoryRepository.findOrCreateByCategory(WorkoutCategory.builder().category("Triceps").build());
        back = workoutCategoryRepository.findOrCreateByCategory(WorkoutCategory.builder().category("Back").build());
        core = workoutCategoryRepository.findOrCreateByCategory(WorkoutCategory.builder().category("Core").build());
        rest = workoutCategoryRepository.findOrCreateByCategory(WorkoutCategory.builder().category("Rest").build());
    }
}
