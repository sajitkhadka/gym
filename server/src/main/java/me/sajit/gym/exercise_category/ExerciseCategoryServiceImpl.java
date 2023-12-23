package me.sajit.gym.exercise_category;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ExerciseCategoryServiceImpl implements ExerciseCategoryService{
    private final ExerciseCategoryRepository exerciseCategoryRepository;
    @Override
    public List<ExerciseCategory> getAllExerciseCategory() {
        return exerciseCategoryRepository.findAll(Sort.by(Sort.Order.asc("")));
    }
}
