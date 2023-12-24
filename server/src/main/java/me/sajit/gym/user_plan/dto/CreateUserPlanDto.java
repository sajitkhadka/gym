package me.sajit.gym.user_plan.dto;

import lombok.Data;
import java.util.List;

@Data
public class CreateUserPlanDto {

    @Data
    public static class ScheduleDto {
        int dayorder;
        int workoutCategoryId;
    }
    String planName;
    List<ScheduleDto> schedules;

    int predefinedPlanId;
}