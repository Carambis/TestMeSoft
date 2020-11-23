package by.bsuir.service;

import by.bsuir.entity.AnswerStatistic;
import by.bsuir.entity.BsuirTask;
import by.bsuir.entity.TaskSequence;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskService {
    Mono<BsuirTask> getTask(String taskRest);

    Flux<TaskSequence> startTest();

    String getNextTask();

    void addUserAnswer(String taskRest, String answer);

    AnswerStatistic checkUserAnswers();
}
