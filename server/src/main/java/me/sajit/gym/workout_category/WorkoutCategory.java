package me.sajit.gym.workout_category;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Categories examples
 * Upper body, Leg workout, Arms, Upper body Push, Upper body pull, Compound, Cardio, Shoulder, Chest, Lower Body, Triceps
 * Biceps, Forearms, Abs, Body Weight
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String category;
}
