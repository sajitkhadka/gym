package me.sajit.gym.user;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.sajit.gym.user.dto.CreateUserPlanDto;
import me.sajit.gym.user_plan.UserPlan;
import me.sajit.gym.user_plan.UserPlanCategorySchedule;
import me.sajit.gym.user_plan.UserPlanRepository;
import me.sajit.gym.workout_category.WorkoutCategory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserPlanRepository userPlanRepository;
    @Override
    public void getUsersNextRoutine() {

    }
    @Override
    public UserPlan createNewUserPlan(CreateUserPlanDto createUserPlanDto) throws Exception {
        Optional<User> user = userRepository.findById(1L);
        if(user.isEmpty()) {
            throw new Exception("User couldn't be found");
        }
        //update the existing plan to be false;
        Optional<UserPlan> existingActiveUserPlan = userPlanRepository.findByUserAndActive(user.get(), true);
        if(existingActiveUserPlan.isPresent()){
            existingActiveUserPlan.get().setActive(false);
            userPlanRepository.save(existingActiveUserPlan.get());
        }

        //Create all schedules in the plan
        List<UserPlanCategorySchedule>  userPlanCategorySchedules = new ArrayList<>();
        for(CreateUserPlanDto.ScheduleDto scheduleDto: createUserPlanDto.getSchedules()){
            WorkoutCategory workoutCategory = WorkoutCategory.builder().id(scheduleDto.getWorkoutCategoryId()).build();
            userPlanCategorySchedules.add(
                    UserPlanCategorySchedule
                            .builder()
                            .dayOrder(scheduleDto.getDayorder())
                            .workoutCategory(workoutCategory)
                            .build()
            );
        }
        //create new userplan
        return UserPlan.builder()
                .user(user.get())
                .createdDate(LocalDateTime.now())
                .name(createUserPlanDto.getPlanName())
                .active(true)
                .categorySchedules(userPlanCategorySchedules)
                .build();
    }
}
