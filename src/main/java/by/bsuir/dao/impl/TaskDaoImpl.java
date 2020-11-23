package by.bsuir.dao.impl;

import by.bsuir.dao.TaskDao;
import by.bsuir.entity.BsuirTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public class TaskDaoImpl implements TaskDao {
    private final ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public TaskDaoImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Flux<String> findAllTestType() {
        return mongoTemplate.findDistinct("taskType", BsuirTask.class, String.class);
    }
}
