package me.sajit.gym.scripts;

import lombok.RequiredArgsConstructor;
import me.sajit.gym.scripts.seeds.ExerciseCategorySeeds;
import me.sajit.gym.scripts.seeds.ExerciseMuscleGroupSeeds;
import me.sajit.gym.scripts.seeds.PredefinedPlanSeeds;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DBSeeder implements CommandLineRunner {

    private final PredefinedPlanSeeds predefinedPlanSeeds;
    private final ExerciseCategorySeeds exerciseCategorySeeds;
    private final ExerciseMuscleGroupSeeds exerciseMuscleGroupSeeds;

    @Override
    public void run(String... args) throws Exception {
        predefinedPlanSeeds.seedNew();
        exerciseCategorySeeds.seedNew();
        exerciseMuscleGroupSeeds.seedNew();
    }

}