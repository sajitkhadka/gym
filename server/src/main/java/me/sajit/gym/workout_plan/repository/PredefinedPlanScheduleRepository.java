package me.sajit.gym.workout_plan.repository;

import me.sajit.gym.workout_plan.domain.PredefinedPlanSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredefinedPlanScheduleRepository extends JpaRepository<PredefinedPlanSchedule, Long> {
}
