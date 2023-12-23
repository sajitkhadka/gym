package me.sajit.gym.workout_plan;

import me.sajit.gym.workout_plan.domain.PredefinedPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredefinedPlanRepository extends JpaRepository<PredefinedPlan, Long> {
}
