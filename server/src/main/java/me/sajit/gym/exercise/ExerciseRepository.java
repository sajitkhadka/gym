package me.sajit.gym.exercise;

import me.sajit.gym.muscle_group.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    Exercise findByName(String propertyValue);
    default Exercise findOrCreateByName(Exercise exercise) {
        Exercise entity = findByName(exercise.name);
        if (entity == null) {
            save(exercise);
        }
        return entity;
    }
}
