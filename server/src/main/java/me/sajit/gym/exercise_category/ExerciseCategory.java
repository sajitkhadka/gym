package me.sajit.gym.exercise_category;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.sajit.gym.exercise.Exercise;
import me.sajit.gym.workout_category.WorkoutCategory;

/**
 * This is relation between each exercise and the category that it would belong to
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseCategory {
    public ExerciseCategory(WorkoutCategory category, Exercise exercise){
        this.category = category;
        this.exercise = exercise;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
//    Long workoutCategoryId;
    @ManyToOne
    @JoinColumn(name="workout_category_id", nullable = false)
    WorkoutCategory category;

    @ManyToOne
    @JoinColumn(name="exercise_id", nullable = false)
    Exercise exercise;

}
