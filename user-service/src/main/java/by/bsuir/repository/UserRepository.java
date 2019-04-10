package by.bsuir.repository;

import org.springframework.data.repository.CrudRepository;
import by.bsuir.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findById(Long id);
}
