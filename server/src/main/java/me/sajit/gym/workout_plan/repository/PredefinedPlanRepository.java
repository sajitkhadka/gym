package me.sajit.gym.workout_plan.repository;

import me.sajit.gym.workout_plan.domain.PredefinedPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredefinedPlanRepository extends JpaRepository<PredefinedPlan, Long> {
    List<PredefinedPlan> findByWorkoutPerCycle(Integer workoutPerCycle);

}
