package by.bsuir.controller;

import by.bsuir.entity.AnswerStatistic;
import by.bsuir.entity.BsuirTask;
import by.bsuir.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class Controller {

    private final TaskService taskService;

    @Autowired
    public Controller(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "/get")
    public String get() {
        return "Success";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/getTask")
    public Mono<BsuirTask> getTask(@RequestParam("taskRest") String taskRest) {
        return taskService.getTask(taskRest);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/getNextTask")
    public String getNextTask() {
        return taskService.getNextTask();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/saveAnswer")
    public void saveAnswer(@RequestParam("taskRest") String taskRest,
                           @RequestParam("answer") String answer) {
        taskService.addUserAnswer(taskRest, answer);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/startTest")
    public Flux<ResponseEntity<String>> startTest() {
        return taskService.startTest().map(taskSequence -> ResponseEntity.ok().build());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/getResult")
    public AnswerStatistic getResult() {
        return taskService.checkUserAnswers();
    }


    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "someWrong")
    @ExceptionHandler(Exception.class)
    public void errorHandler(){

    }
}
