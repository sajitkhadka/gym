package me.sajit.gym.user_plan;

import me.sajit.gym.user.User;
import me.sajit.gym.user_plan.domain.UserPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPlanRepository extends JpaRepository<UserPlan, Long> {
    Optional<UserPlan> findByUserAndActive(User user, boolean active);
}
