package me.sajit.gym.scripts.seeds;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.sajit.gym.exercise.Exercise;
import me.sajit.gym.exercise.ExerciseRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Data
@Component
public class ExerciseSeeds {
    private final ExerciseRepository exerciseRepository;
    private Exercise squats;
    private Exercise deadlifts;
    private Exercise legPress;
    private Exercise lunges;
    private Exercise legExtensions;
    private Exercise legCurls;
    private Exercise calfRaises;
    private Exercise stepUps;
    private Exercise hackSquats;
    private Exercise boxJumps;
    private Exercise bulgarianSplitSquats;
    private Exercise goodMornings;
    private Exercise seatedLegPress;
    private Exercise sumoDeadlifts;

    @PostConstruct
    public void init(){
        squats =  exerciseRepository.findOrCreateByName(Exercise.builder().name("Squats").build());
        deadlifts = exerciseRepository.findOrCreateByName(Exercise.builder().name("Deadlifts").build());
        legPress = exerciseRepository.findOrCreateByName(Exercise.builder().name("Leg Press").build());
        lunges = exerciseRepository.findOrCreateByName(Exercise.builder().name("Lunges").build());
        legExtensions = exerciseRepository.findOrCreateByName(Exercise.builder().name("Leg Extensions").build());
        legCurls = exerciseRepository.findOrCreateByName(Exercise.builder().name("Leg Curls").build());
        calfRaises = exerciseRepository.findOrCreateByName(Exercise.builder().name("Calf Raises").build());
        stepUps = exerciseRepository.findOrCreateByName(Exercise.builder().name("Step-Ups").build());
        hackSquats = exerciseRepository.findOrCreateByName(Exercise.builder().name("Hack Squats").build());
        boxJumps = exerciseRepository.findOrCreateByName(Exercise.builder().name("Box Jumps").build());
        bulgarianSplitSquats = exerciseRepository.findOrCreateByName(Exercise.builder().name("Bulgarian Split Squats").build());
        goodMornings = exerciseRepository.findOrCreateByName(Exercise.builder().name("Good Mornings").build());
        seatedLegPress = exerciseRepository.findOrCreateByName(Exercise.builder().name("Seated Leg Press").build());
        sumoDeadlifts = exerciseRepository.findOrCreateByName(Exercise.builder().name("Sumo Deadlifts").build());
    }
}
