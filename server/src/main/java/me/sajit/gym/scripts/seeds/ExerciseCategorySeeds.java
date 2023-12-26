package me.sajit.gym.scripts.seeds;

import lombok.RequiredArgsConstructor;
import me.sajit.gym.exercise_category.ExerciseCategory;
import me.sajit.gym.exercise_category.ExerciseCategoryRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExerciseCategorySeeds implements ISeeder{

    private final CategorySeeds categorySeeds;
    private final ExerciseSeeds exerciseSeeds;

    private final ExerciseCategoryRepository exerciseCategoryRepository;
    @Override
    public void seedOld() {
        List<ExerciseCategory> categories  = List.of(
                new ExerciseCategory(categorySeeds.getLowerBody(), exerciseSeeds.getSquats()),
                new ExerciseCategory(categorySeeds.getLowerBody(), exerciseSeeds.getDeadlifts()),
                new ExerciseCategory(categorySeeds.getLowerBody(), exerciseSeeds.getLegPress()),
                new ExerciseCategory(categorySeeds.getLowerBody(), exerciseSeeds.getLunges()),
                new ExerciseCategory(categorySeeds.getLowerBody(), exerciseSeeds.getLegExtensions()),
                new ExerciseCategory(categorySeeds.getLowerBody(), exerciseSeeds.getLegCurls()),
                new ExerciseCategory(categorySeeds.getLowerBody(), exerciseSeeds.getCalfRaises()),
                new ExerciseCategory(categorySeeds.getLowerBody(), exerciseSeeds.getStepUps()),
                new ExerciseCategory(categorySeeds.getLowerBody(), exerciseSeeds.getHackSquats()),
                new ExerciseCategory(categorySeeds.getLowerBody(), exerciseSeeds.getBoxJumps()),
                new ExerciseCategory(categorySeeds.getLowerBody(), exerciseSeeds.getBulgarianSplitSquats()),
                new ExerciseCategory(categorySeeds.getLowerBody(), exerciseSeeds.getGoodMornings()),
                new ExerciseCategory(categorySeeds.getLowerBody(), exerciseSeeds.getSeatedLegPress()),
                new ExerciseCategory(categorySeeds.getLowerBody(), exerciseSeeds.getSumoDeadlifts())
        );
        exerciseCategoryRepository.saveAll(categories);
    }

    @Override
    public void seedNew() {
        if(exerciseCategoryRepository.findAll(PageRequest.of(0,1)).getContent().isEmpty()){
            seedOld();
        }
    }
}
