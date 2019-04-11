package by.bsuir.repository;

import org.springframework.data.repository.CrudRepository;
import by.bsuir.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, String> {
    UserEntity findById(String id);
}
