package by.bsuir.dao;

import by.bsuir.entity.BsuirTask;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface CrudTaskDao extends ReactiveCrudRepository<BsuirTask, String> {
    @Query(value = "{ 'taskType' : ?0 }", fields = "{ 'taskRest' : 1}")
    Mono<List<BsuirTask>> findByTaskType(String taskType);

    @Query(value = "{ 'taskRest' : ?0 }", fields = "{'taskName': 1, 'header': 1, 'taskType': 1, 'answers': 1 }")
    Mono<BsuirTask> findByTaskRest(String taskRest);

    @Query(value = "{ 'taskRest' : ?0 }", fields = "{'rightAnswer':1 , 'taskType':1}")
    Mono<BsuirTask> findTaskByTaskRest(String taskRest);

    Mono<Integer> countByTaskType(String taskType);
}
