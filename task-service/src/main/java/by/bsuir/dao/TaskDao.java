package by.bsuir.dao;

import by.bsuir.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskDao extends CrudRepository<Task, Long> {
}
