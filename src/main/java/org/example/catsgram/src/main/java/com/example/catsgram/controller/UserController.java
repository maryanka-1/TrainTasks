package com.example.catsgram.controller;

import com.example.catsgram.exceptions.InvalidEmailException;
import com.example.catsgram.exceptions.UserAlreadyExistException;
import com.example.catsgram.model.User;
import com.example.catsgram.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.zalando.logbook.*;
import java.io.IOException;
import java.util.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @GetMapping
    public Collection<User> getUsers() throws IOException {
        Collection<User> listUsers = service.getListUsers().values();
        log.debug("количество пользователей на данный момент = {}", listUsers.size());
        return listUsers;
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        service.addUser(newUser);
        log.info("Пользователь {} был создан", newUser.toString());
        return newUser;
    }

    @PutMapping
    public User updateUser(@RequestBody User newUser) {
        service.addUser(newUser);
        log.info("Пользователь {} был обновлен", newUser.toString());
        return newUser;
    }

    @GetMapping("/user/{email}")
    public User getUserById(@PathVariable("email") String email){
        User result = service.findUserByEmail(email);
        log.info("Пользователь с email: {} - {}", email, result.toString());
        return result;
    }

}
