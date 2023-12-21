package me.sajit.gym.exercise_muscle_group;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.sajit.gym.exercise.Exercise;
import me.sajit.gym.muscle_group.MuscleGroup;

/**
 * Relation between each muscle group trained by an exercise
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseMuscleGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="exercise_id", nullable = false)
    Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "muscle_group_id", nullable = false)
    MuscleGroup muscleGroup;
}
