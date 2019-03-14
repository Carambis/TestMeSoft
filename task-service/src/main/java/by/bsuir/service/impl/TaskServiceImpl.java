package by.bsuir.service.impl;

import by.bsuir.dao.TaskDao;
import by.bsuir.entity.Task;
import by.bsuir.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskDao taskDao;

    @Autowired
    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public Task getTask(Long id) {
        return taskDao.findOne(id);
    }
}
