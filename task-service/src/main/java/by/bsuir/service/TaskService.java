package by.bsuir.service;

import by.bsuir.entity.AnswerStatistic;
import by.bsuir.entity.Task;

public interface TaskService {
    Task getTask(String taskRest);

    void startTest();

    String getNextTask();

    void addUserAnswer(String taskRest, String answer);

    AnswerStatistic checkUserAnswers();
}
