package by.bsuir.dao;

import by.bsuir.entity.AnswerStatisticLog;
import org.springframework.data.repository.CrudRepository;

public interface AnswerStatisticDao extends CrudRepository<AnswerStatisticLog, String> {
}
