package me.sajit.gym.user_plan;

import me.sajit.gym.user_plan.dto.CreateUserPlanDto;

public interface UserPlanService {
    public UserPlan createUserplan(CreateUserPlanDto createUserPlanDto);
}
