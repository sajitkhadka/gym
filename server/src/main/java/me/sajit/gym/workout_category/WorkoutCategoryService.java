package me.sajit.gym.workout_category;

import java.util.List;

public interface WorkoutCategoryService{
    public List<WorkoutCategory> getAllWorkoutCategories();
    WorkoutCategory getWorkoutCategoryById(Long categoryId);
}
