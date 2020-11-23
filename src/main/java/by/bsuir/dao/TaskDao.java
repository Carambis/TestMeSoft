package by.bsuir.dao;

import reactor.core.publisher.Flux;

public interface TaskDao {
    Flux<String> findAllTestType();
}
