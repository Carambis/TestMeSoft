package by.bsuir.controller;

import by.bsuir.entity.Task;
import by.bsuir.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private final TaskService taskService;

    @Autowired
    public Controller(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "/get")
    public String get(){
        return "Success";
    }

    @PostMapping(value = "/getTask")
    public Task getTask(@RequestParam("id") String id){
        return taskService.getTask(id);
    }

    @GetMapping(value = "/getNextTask")
    public String getNextTask(){
        return "someTask";
    }

    @GetMapping(value = "/startTest")
    public void startTest(){
        taskService.startTest();
    }
}
