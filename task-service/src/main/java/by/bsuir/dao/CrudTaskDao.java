package by.bsuir.dao;

import by.bsuir.entity.Task;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrudTaskDao extends CrudRepository<Task, String> {
    @Query(value = "{ 'taskType' : ?0 }", fields = "{ 'taskRest' : 1}")
    List<Task> findByTaskType(String taskType);

    @Query(value = "{ 'id' : ?0 }", fields = "{ 'id' : 1, 'taskName': 1, 'taskType': 1, 'questions': 1 }")
    Task findById(String id);

    @Query(value = "{ 'id' : ?0 }", fields = "{'rightQuestion':1 , 'taskType':1}")
    Task findTaskById(String id);

    int countByTaskType(String taskType);
}
