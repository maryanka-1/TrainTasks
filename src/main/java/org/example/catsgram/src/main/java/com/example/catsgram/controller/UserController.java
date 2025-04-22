package com.example.catsgram.controller;

import com.example.catsgram.exceptions.InvalidEmailException;
import com.example.catsgram.exceptions.UserAlreadyExistException;
import com.example.catsgram.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Map<String, User> users = new HashMap<>();

    @GetMapping
    public Collection<User> getUsers(){
        return users.values();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser){
        if(newUser.getEmail()==null || newUser.getEmail().isEmpty()){
            throw new InvalidEmailException("Адрес электронной почты не может быть пустым.");
        }
        if(users.containsKey(newUser.getEmail())){
            throw new UserAlreadyExistException("Пользователь с электронной почтой " +
                    newUser.getEmail() + " уже зарегистрирован.");
        }
        users.put(newUser.getEmail(), newUser);
        return newUser;
    }

    @PutMapping
    public User updateUser(@RequestBody User newUser){
        if(newUser.getEmail()==null || newUser.getEmail().isEmpty()){
            throw new InvalidEmailException("Адрес электронной почты не может быть пустым.");
        }
        if(newUser.getNickName()==null || newUser.getNickName().isEmpty()){
            throw new InvalidEmailException("Ник не может быть пустым.");
        }
        if(newUser.getBirthday()==null){
            throw new InvalidEmailException("Дата рождения должна быть заполнена.");
        }
        users.put(newUser.getEmail(), newUser);
        return newUser;
    }


}
