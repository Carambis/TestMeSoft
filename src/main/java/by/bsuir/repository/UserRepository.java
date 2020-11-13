package by.bsuir.repository;

import by.bsuir.entity.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends ReactiveMongoRepository<UserEntity, String> {
}
