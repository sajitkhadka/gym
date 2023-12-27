package me.sajit.gym.exercise_muscle_group;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class ExerciseMuscleGroup {

    public enum Target {
        Minor,
        Major
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="exercise_id", nullable = false)
    Exercise exercise;


    @JoinColumn(name = "muscle_group_id", nullable = false)
    @ManyToOne
    MuscleGroup muscleGroup;

    @Column
    @Enumerated(EnumType.ORDINAL)
    Target target;
}
