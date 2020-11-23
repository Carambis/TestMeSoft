package by.bsuir.dao;

import by.bsuir.entity.AnswerStatisticLog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerStatisticDao extends ReactiveMongoRepository<AnswerStatisticLog, String> {
}
