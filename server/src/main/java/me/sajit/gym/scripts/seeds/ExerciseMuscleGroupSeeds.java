package me.sajit.gym.scripts.seeds;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.sajit.gym.exercise_muscle_group.ExerciseMuscleGroup;
import me.sajit.gym.exercise_muscle_group.ExerciseMuscleGroupRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Transactional
public class ExerciseMuscleGroupSeeds implements ISeeder {
    private final ExerciseSeeds exerciseSeeds;
    private final MuscleGroupSeeds muscleGroupSeeds;
    private final ExerciseMuscleGroupRepository exerciseMuscleGroupRepository;

    @Override
    public void seedOld() {
        List<ExerciseMuscleGroup> emg = List.of(
                //squat major and minor muscle groups
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getSquats()).muscleGroup(muscleGroupSeeds.getQuadriceps()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getSquats()).muscleGroup(muscleGroupSeeds.getHamstrings()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getSquats()).muscleGroup(muscleGroupSeeds.getGlutes()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getSquats()).muscleGroup(muscleGroupSeeds.getAdductors()).target(ExerciseMuscleGroup.Target.Minor).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getSquats()).muscleGroup(muscleGroupSeeds.getCalves()).target(ExerciseMuscleGroup.Target.Minor).build(),

                //Dead lifts major and minor muscle groups
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getDeadlifts()).muscleGroup(muscleGroupSeeds.getHamstrings()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getDeadlifts()).muscleGroup(muscleGroupSeeds.getGlutes()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getDeadlifts()).muscleGroup(muscleGroupSeeds.getLowerBack()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getDeadlifts()).muscleGroup(muscleGroupSeeds.getQuadriceps()).target(ExerciseMuscleGroup.Target.Minor).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getDeadlifts()).muscleGroup(muscleGroupSeeds.getCalves()).target(ExerciseMuscleGroup.Target.Minor).build(),

                //Leg press
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getLegPress()).muscleGroup(muscleGroupSeeds.getQuadriceps()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getLegPress()).muscleGroup(muscleGroupSeeds.getHamstrings()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getLegPress()).muscleGroup(muscleGroupSeeds.getGlutes()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getLegPress()).muscleGroup(muscleGroupSeeds.getCalves()).target(ExerciseMuscleGroup.Target.Minor).build(),

                //Leg Extensions
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getLegExtensions()).muscleGroup(muscleGroupSeeds.getQuadriceps()).target(ExerciseMuscleGroup.Target.Major).build(),

                //leg curls
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getLegCurls()).muscleGroup(muscleGroupSeeds.getHamstrings()).target(ExerciseMuscleGroup.Target.Major).build(),

                //Calf raises
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getCalfRaises()).muscleGroup(muscleGroupSeeds.getCalves()).target(ExerciseMuscleGroup.Target.Major).build(),

                //lunges
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getLunges()).muscleGroup(muscleGroupSeeds.getQuadriceps()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getLunges()).muscleGroup(muscleGroupSeeds.getHamstrings()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getLunges()).muscleGroup(muscleGroupSeeds.getGlutes()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getLunges()).muscleGroup(muscleGroupSeeds.getCalves()).target(ExerciseMuscleGroup.Target.Minor).build(),

                //Step ups
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getStepUps()).muscleGroup(muscleGroupSeeds.getQuadriceps()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getStepUps()).muscleGroup(muscleGroupSeeds.getGlutes()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getStepUps()).muscleGroup(muscleGroupSeeds.getHamstrings()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getStepUps()).muscleGroup(muscleGroupSeeds.getCalves()).target(ExerciseMuscleGroup.Target.Minor).build(),

                //Hack Squats
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getHackSquats()).muscleGroup(muscleGroupSeeds.getQuadriceps()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getHackSquats()).muscleGroup(muscleGroupSeeds.getGlutes()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getHackSquats()).muscleGroup(muscleGroupSeeds.getHamstrings()).target(ExerciseMuscleGroup.Target.Minor).build(),

                //Box Jumps
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getBoxJumps()).muscleGroup(muscleGroupSeeds.getQuadriceps()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getBoxJumps()).muscleGroup(muscleGroupSeeds.getGlutes()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getBoxJumps()).muscleGroup(muscleGroupSeeds.getHamstrings()).target(ExerciseMuscleGroup.Target.Minor).build(),

                //Bulgarian Split Squats
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getBulgarianSplitSquats()).muscleGroup(muscleGroupSeeds.getQuadriceps()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getBulgarianSplitSquats()).muscleGroup(muscleGroupSeeds.getGlutes()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getBulgarianSplitSquats()).muscleGroup(muscleGroupSeeds.getHamstrings()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getBulgarianSplitSquats()).muscleGroup(muscleGroupSeeds.getCalves()).target(ExerciseMuscleGroup.Target.Minor).build(),

                //Good Mornings
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getGoodMornings()).muscleGroup(muscleGroupSeeds.getHamstrings()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getGoodMornings()).muscleGroup(muscleGroupSeeds.getLowerBack()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getGoodMornings()).muscleGroup(muscleGroupSeeds.getGlutes()).target(ExerciseMuscleGroup.Target.Minor).build(),

                //Seated Leg Press
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getSeatedLegPress()).muscleGroup(muscleGroupSeeds.getQuadriceps()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getSeatedLegPress()).muscleGroup(muscleGroupSeeds.getHamstrings()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getSeatedLegPress()).muscleGroup(muscleGroupSeeds.getGlutes()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getSeatedLegPress()).muscleGroup(muscleGroupSeeds.getCalves()).target(ExerciseMuscleGroup.Target.Minor).build(),

                //Sumo DeadLifts
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getSumoDeadlifts()).muscleGroup(muscleGroupSeeds.getAdductors()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getSumoDeadlifts()).muscleGroup(muscleGroupSeeds.getGlutes()).target(ExerciseMuscleGroup.Target.Major).build(),
                ExerciseMuscleGroup.builder().exercise(exerciseSeeds.getSumoDeadlifts()).muscleGroup(muscleGroupSeeds.getHamstrings()).target(ExerciseMuscleGroup.Target.Major).build());
        exerciseMuscleGroupRepository.saveAll(emg);
    }

    @Override
    public void seedNew() {
        if(exerciseMuscleGroupRepository.findAll(PageRequest.of(0,1)).getContent().isEmpty()){
            seedOld();
        }
    }
}
