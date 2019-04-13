package by.bsuir.service;

import by.bsuir.entity.AnswerStatistic;
import by.bsuir.entity.Task;

public interface TaskService {
    Task getTask(String id);

    void startTest();

    String getNextTask();

    void addUserAnswer(String taskId, String answer);

    AnswerStatistic checkUserAnswers();
}
