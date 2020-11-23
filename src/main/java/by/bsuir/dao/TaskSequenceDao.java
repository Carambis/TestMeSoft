package by.bsuir.dao;

import by.bsuir.entity.TaskSequence;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TaskSequenceDao extends ReactiveMongoRepository<TaskSequence, String> {
    Mono<TaskSequence> getByUserId(String userId);
}
