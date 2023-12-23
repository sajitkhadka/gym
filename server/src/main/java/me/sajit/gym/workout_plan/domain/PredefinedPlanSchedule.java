package me.sajit.gym.workout_plan;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.sajit.gym.user.User;
import me.sajit.gym.workout_category.WorkoutCategory;

/**
 * This is the predefined workout category schedule from which user can select. this can be all workout categories that can
 * be repeated over and over again in the order that user decides. By default, it won't have order, once user picks one predefined
 * category they will have option to re-order them.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredefinedPlanCategorySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "predefined_plan_id", nullable = false)
    private PredefinedPlan predefinedPlan;
    //order of each category by which each day the schedule would be followed


}
