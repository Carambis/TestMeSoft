package by.bsuir.controller;

import by.bsuir.entity.Task;
import by.bsuir.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Task getTask(@RequestParam("id") Long id){
        return taskService.getTask(id);
    }
}
