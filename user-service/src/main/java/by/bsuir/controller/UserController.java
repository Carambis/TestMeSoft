package by.bsuir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service_client.data.User;
import service_client.data.request.UserCreation;
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
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<String> login(@RequestParam(value = "login") final String login,
                                 @RequestParam(value = "password") final String password) {
        return run(() -> userService.login(login, password));
    }

    @Secured("ROLE_ANONYMOUS")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<User> add(@RequestBody final UserCreation user) {
        return run(() -> userService.add(user));
    }


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<User> get(@PathVariable final Long id) {
        return run(() -> userService.get(id));
    }
}