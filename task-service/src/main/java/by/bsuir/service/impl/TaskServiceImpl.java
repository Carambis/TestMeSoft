package by.bsuir.service.impl;

import by.bsuir.dao.TaskDao;
import by.bsuir.entity.Task;
import by.bsuir.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskDao taskDao;

    @Autowired
    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public Task getTask(String id) {
        return taskDao.findOne(id);
    }

    @Override
    public void startTest() {
        List<String> taskSequence = new ArrayList<>();
        List<Task> taskList = taskDao.findByTaskType("type");
        taskSequence.addAll(getShuffleTask(taskList));
        taskList = taskDao.findByTaskType("type2");
        taskSequence.addAll(getShuffleTask(taskList));
        SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println();
    }

    private List<String> getShuffleTask(List<Task> taskList){
        List<String> taskIds = new ArrayList<>();
        for (Task task:taskList) {
            taskIds.add(task.getId());
        }
        Collections.shuffle(taskIds);
        return taskIds;
    }
}
