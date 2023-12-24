package me.sajit.gym.user_plan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.sajit.gym.user.User;
import me.sajit.gym.workout_category.WorkoutCategory;

/**
 * For user's plan, this includes all exercise routine, which would be repeated in the given order
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPlanCategorySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_plan_id", nullable = false)
    @JsonIgnoreProperties("categorySchedules")
    @JsonIgnore
    private UserPlan userPlan;
    //order of each category by which each day the schedule would be followed
    @Column
    int dayOrder;

    @ManyToOne
    @JoinColumn(name = "workout_category_id", nullable = false)
    private WorkoutCategory workoutCategory;

}
