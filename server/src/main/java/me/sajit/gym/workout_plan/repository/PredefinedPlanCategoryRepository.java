package me.sajit.gym.workout_plan.repository;

import me.sajit.gym.workout_plan.domain.PredefinedPlanCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredefinedPlanCategoryRepository extends JpaRepository<PredefinedPlanCategory, Long> {
}
