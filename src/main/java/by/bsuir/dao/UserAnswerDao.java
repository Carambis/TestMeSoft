package by.bsuir.dao;

import by.bsuir.entity.UserAnswer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface UserAnswerDao extends ReactiveMongoRepository<UserAnswer, String> {
    Mono<List<UserAnswer>> findAllByUserId(String userId);
}
