package by.bsuir.dao;

import by.bsuir.entity.TaskTypeRecommendation;
import org.springframework.data.repository.CrudRepository;

public interface RecommendationDao extends CrudRepository<TaskTypeRecommendation, String> {
    TaskTypeRecommendation findByMaximumValueGreaterThanAndMinimumValueLessThanEqualAndTaskType(int percent1, int percent2, String taskType);
}
