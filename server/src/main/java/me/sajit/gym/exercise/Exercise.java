package me.sajit.gym.exercise;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Each exercise that can be done in sets and reps
 * For eg, Dumbbell Press, Squat, Push-ups, Pull-ups, Chin-ups etc
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;
}
