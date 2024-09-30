package com.fram.controllers;

import com.fram.models.Users;
import com.fram.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
    @Autowired
    UserService userService;



    @PostMapping("/create")
    public Users createUser(@RequestBody Users user){
    return userService.register(user);
    }
    @PostMapping("/sign-in")
    public String login(@RequestBody Users user){
        log.info("The user "+ user);
        return userService.verifyUser(user);
    }
}
