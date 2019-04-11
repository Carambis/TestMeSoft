package by.bsuir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service_client.data.User;
import service_client.result.Result;
import by.bsuir.service.UserService;

import static service_client.result.Result.run;


@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Secured("ROLE_ANONYMOUS")
    @PostMapping(value = "/login")
    public Result<String> login(@RequestParam(value = "firstName") final String firstName,
                                @RequestParam(value = "lastName") final String lastName,
                                @RequestParam(value = "groupNumber") final String groupNumber) {
        return run(() -> userService.login(firstName, lastName, groupNumber));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/get/{id}")
    public Result<User> get(@PathVariable final String id) {
        return run(() -> userService.get(id));
    }
}