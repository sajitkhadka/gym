package me.sajit.gym.workout_category;

import lombok.RequiredArgsConstructor;
import me.sajit.gym.shared.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/workout-category")
public class WorkoutCategoryResource {
    private final WorkoutCategoryService workoutCategoryService;
    @GetMapping()
    ResponseEntity<Response> getAllWorkoutCategories(){
        return ResponseEntity.ok(Response
                .builder()
                .message("Successfully fetched all categories")
                        .status(HttpStatus.OK)
                        .data(Map.of("categories", workoutCategoryService.getAllWorkoutCategories()))
                .build());
    }
}
