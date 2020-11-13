package by.bsuir.dao;

import by.bsuir.entity.UserAnswer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserAnswerDao extends CrudRepository<UserAnswer, String> {
    List<UserAnswer> findAllByUserId(String userId);
}
