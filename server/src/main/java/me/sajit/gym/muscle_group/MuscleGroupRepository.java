package me.sajit.gym.muscle_group;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MuscleGroupRepository extends JpaRepository<MuscleGroup, Long> {
    MuscleGroup findByMuscle(String propertyValue);
    default MuscleGroup findOrCreateByMuscle(MuscleGroup muscleGroup) {
        MuscleGroup entity = findByMuscle(muscleGroup.muscle);
        if (entity == null) {
            entity = save(muscleGroup);
        }
        return entity;
    }
}
