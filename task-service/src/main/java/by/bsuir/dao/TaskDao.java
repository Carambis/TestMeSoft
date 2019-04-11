package by.bsuir.dao;

import by.bsuir.entity.Task;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskDao extends CrudRepository<Task, String> {
    @Query(value="{ 'taskType' : ?0 }", fields="{ 'id' : 1}")
    List<Task> findByTaskType(String taskType);
}
