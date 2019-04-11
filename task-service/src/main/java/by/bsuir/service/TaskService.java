package by.bsuir.service;

import by.bsuir.entity.Task;

public interface TaskService {
    Task getTask(String id);

    void startTest();
}
