package me.sajit.gym.workout_plan;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * In built workout plan from which users can select a plan, It could be something like
 * 2 days split variation 1, 3 days split variant 1, 3 days split, Arnold Split etc
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredefinedPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    int workoutPerWeek;

    @Column
    String details;
}
