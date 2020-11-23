package by.bsuir.dao;

import by.bsuir.entity.TaskTypeRecommendation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RecommendationDao extends ReactiveMongoRepository<TaskTypeRecommendation, String> {
    Mono<TaskTypeRecommendation> findByMaximumValueGreaterThanEqualAndMinimumValueLessThanEqualAndTaskType(int percent1, int percent2, String taskType);
}
