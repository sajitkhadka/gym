package me.sajit.gym.user_plan;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.sajit.gym.user.User;
import me.sajit.gym.user.UserService;
import me.sajit.gym.user_plan.domain.UserPlan;
import me.sajit.gym.user_plan.domain.UserPlanCategorySchedule;
import me.sajit.gym.user_plan.dto.CreateUserPlanDto;
import me.sajit.gym.workout_category.WorkoutCategory;
import me.sajit.gym.workout_plan.PredefinedPlanService;
import me.sajit.gym.workout_plan.domain.PredefinedPlan;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserPlanServiceImpl implements UserPlanService {

    private final UserService userService;
    private final UserPlanRepository userPlanRepository;

    private final PredefinedPlanService predefinedPlanService;

    @Override
    public UserPlan createUserplan(CreateUserPlanDto createUserPlanDto) {
        User user = userService.getUserById(1);
        //update the existing plan to be false;
        Optional<UserPlan> existingActiveUserPlan = userPlanRepository.findByUserAndActive(user, true);
        if (existingActiveUserPlan.isPresent()) {
            existingActiveUserPlan.get().setActive(false);
            userPlanRepository.save(existingActiveUserPlan.get());
        }

        //create new userplan
        UserPlan userplan =  UserPlan.builder()
                .user(user)
                .createdDate(LocalDateTime.now())
                .name(createUserPlanDto.getPlanName())
                .active(true)
                .build();
        if(createUserPlanDto.getPredefinedPlanId() > 0) {
            PredefinedPlan predefinedPlan = PredefinedPlan.builder().id((long) createUserPlanDto.getPredefinedPlanId()).build();
            userplan.setPredefinedPlan(predefinedPlan);
        }

        //Create all schedules in the plan
        List<UserPlanCategorySchedule> userPlanCategorySchedules = new ArrayList<>();
        for (CreateUserPlanDto.ScheduleDto scheduleDto : createUserPlanDto.getSchedules()) {
            WorkoutCategory workoutCategory = WorkoutCategory.builder().id((long) scheduleDto.getWorkoutCategoryId()).build();
            userPlanCategorySchedules.add(
                    UserPlanCategorySchedule
                            .builder()
                            .dayOrder(scheduleDto.getDayorder())
                            .user(user)
                            .workoutCategory(workoutCategory)
                            .userPlan(userplan)
                            .build()
            );
        }
        userplan.setCategorySchedules(userPlanCategorySchedules);
        return userPlanRepository.save(userplan);
    }

    @Override
    public UserPlan getActiveUserPlan() {
        User user = userService.getUserById(1);
        return userPlanRepository.findByUserAndActive(user, true).orElseThrow();
    }
}
