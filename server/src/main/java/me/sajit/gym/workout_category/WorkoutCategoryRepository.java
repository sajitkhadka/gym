package me.sajit.gym.workout_category;

import me.sajit.gym.exercise.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutCategoryRepository extends JpaRepository<WorkoutCategory, Long> {
    WorkoutCategory findByCategory(String propertyValue);
    default WorkoutCategory findOrCreateByCategory(WorkoutCategory category) {
        WorkoutCategory entity = findByCategory(category.category);
        if (entity == null) {
            entity = save(category);
        }
        return entity;
    }
}
