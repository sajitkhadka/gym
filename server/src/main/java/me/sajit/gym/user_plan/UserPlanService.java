package me.sajit.gym.user_plan;

import me.sajit.gym.user_plan.domain.UserPlan;
import me.sajit.gym.user_plan.dto.CreateUserPlanDto;

public interface UserPlanService {
     UserPlan createUserplan(CreateUserPlanDto createUserPlanDto);
     UserPlan getActiveUserPlan();
}
