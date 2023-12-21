package me.sajit.gym.workout_history;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.sajit.gym.user.User;
import me.sajit.gym.user_plan.UserPlanCategorySchedule;

import java.time.LocalDateTime;

/**
 * This is each workout history for the individual user. This can be used to generate what workout to do today, based on the
 *  history and UserPlanCategorySchedule table
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    LocalDateTime workoutDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "user_plan_category_schedule_id", nullable = false)
    private UserPlanCategorySchedule userPlanCategorySchedule;
}
