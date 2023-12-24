package me.sajit.gym.user_plan;

import lombok.RequiredArgsConstructor;
import me.sajit.gym.shared.Response;
import me.sajit.gym.user_plan.dto.CreateUserPlanDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/user-plan")
@RequiredArgsConstructor
public class UserPlanResource {

    private final UserPlanService userPlanService;
    @PostMapping
    public ResponseEntity<Response> createPlan(@RequestBody CreateUserPlanDto createUserPlanDto) {
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .data(Map.of("plan", userPlanService.createUserplan(createUserPlanDto)))
                            .message("Successfully created plan")
                            .status(HttpStatus.OK)
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Response.builder().data(Map.of("message", e.getMessage())).build());
        }
    }

}
