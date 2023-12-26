package me.sajit.gym.muscle_group;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Each muscle group of the body
 * Legs, Pecs, Biceps, Forearms, Triceps, Abdominal, Delts, Lats, Glutes, Hip Adductors, Hamstrings, Quads etc.
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MuscleGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String muscle;
}
