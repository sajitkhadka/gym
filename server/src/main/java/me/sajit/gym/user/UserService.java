package me.sajit.gym.user;

import me.sajit.gym.user.dto.CreateUserPlanDto;
import me.sajit.gym.user_plan.UserPlan;

public interface UserService {
    public void getUsersNextRoutine();
    public UserPlan createNewUserPlan(CreateUserPlanDto createUserPlanDto) throws Exception;
}
