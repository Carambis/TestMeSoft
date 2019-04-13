package by.bsuir.dao;

import by.bsuir.entity.TaskSequence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface TaskSequenceDao extends CrudRepository<TaskSequence, String> {
    TaskSequence getByUserId(String userId);
}
