package me.sajit.gym.workout_plan.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.sajit.gym.workout_category.WorkoutCategory;

/**
 * This is the predefined workout category schedule from which user can select. this can be all workout categories that can
 * be repeated over and over again in the order that user decides. By default, it won't have order, once user picks one predefined
 * category they will have option to re-order them. However, for each day plan can have multiple categories which will be grouped in
 * PredefinedPlanCategorySchedule table
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredefinedPlanCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "workout_category_id", nullable = false)
    private WorkoutCategory workoutCategory;

    @ManyToOne
    @JoinColumn(name ="predefined_plan_category_id", nullable = false)
    @JsonIgnoreProperties("predefinedPlanCategories")
    private PredefinedPlanSchedule predefinedPlanSchedule;
}
