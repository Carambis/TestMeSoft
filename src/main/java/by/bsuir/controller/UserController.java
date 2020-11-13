package by.bsuir.controller;

import by.bsuir.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import by.bsuir.service_client.data.User;
import by.bsuir.service.UserService;


@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(value = "/login")
    public Mono<String> login(@RequestBody UserEntity userEntity) {
        return userService.login(userEntity);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/get/{id}")
    public Mono<User> get(@PathVariable final String id) {
        return userService.get(id);
    }

    @GetMapping(value = "/test")
    public String login() {
        return "all ok";
    }

}