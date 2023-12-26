package me.sajit.gym.scripts.seeds;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.sajit.gym.muscle_group.MuscleGroup;
import me.sajit.gym.muscle_group.MuscleGroupRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Data
@Component
public class MuscleGroupSeeds {
    private MuscleGroup quadriceps;
    private MuscleGroup hamstrings;
    private MuscleGroup glutes;
    private MuscleGroup adductors;
    private MuscleGroup calves;
    private MuscleGroup lowerBack;

    private final MuscleGroupRepository muscleGroupRepository;
    @PostConstruct
    public void init() {
        quadriceps = muscleGroupRepository.findOrCreateByMuscle(MuscleGroup.builder().muscle("Quadriceps (Quads)").build());
        hamstrings = muscleGroupRepository.findOrCreateByMuscle(MuscleGroup.builder().muscle("Hamstrings").build());
        glutes = muscleGroupRepository.findOrCreateByMuscle(MuscleGroup.builder().muscle("Gluteus Maximus (Glutes)").build());
        adductors = muscleGroupRepository.findOrCreateByMuscle(MuscleGroup.builder().muscle("Inner Thighs (Adductors)").build());
        calves = muscleGroupRepository.findOrCreateByMuscle(MuscleGroup.builder().muscle("Calves").build());
        lowerBack = muscleGroupRepository.findOrCreateByMuscle(MuscleGroup.builder().muscle("Lower Back").build());
    }
}
