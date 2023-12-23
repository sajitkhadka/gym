package me.sajit.gym.workout_category;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutCategoryServiceImpl implements WorkoutCategoryService{

    private final WorkoutCategoryRepository repository;
    @Override
    public List<WorkoutCategory> getAllWorkoutCategories() {
        return repository.findAll(Sort.by(Sort.Order.asc("category")));
    }

    @Override
    public WorkoutCategory getWorkoutCategoryById(int categoryId) {
        return repository.findById((long) categoryId).orElseThrow();
    }
}
