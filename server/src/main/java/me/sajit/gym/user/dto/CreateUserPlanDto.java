package me.sajit.gym.user.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateUserPlanDto {

    @Data
   public static class ScheduleDto {
        int dayorder;
        Long workoutCategoryId;
    }
    String planName;
    List<ScheduleDto> schedules;
}
