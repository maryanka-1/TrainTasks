package com.example.catsgram.service;

import com.example.catsgram.exceptions.InvalidEmailException;
import com.example.catsgram.exceptions.InvalidNickException;
import com.example.catsgram.exceptions.UserAlreadyExistException;
import com.example.catsgram.model.User;
import com.example.catsgram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public Map<String, User> getListUsers() {
        return repository.getListUsers();
    }

    public void addUser(User user) {
        if (user.getEmail().isEmpty()) {
            throw new InvalidEmailException("Адрес электронной почты не может быть пустым.");
        }
        if (repository.getListUsers().containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("Пользователь с электронной почтой " +
                    user.getEmail() + " уже зарегистрирован.");
        }
        for(User eachUser: repository.getListUsers().values()){
            if(user.getNickname().equals(eachUser.getNickname())){
                throw new UserAlreadyExistException("Ник занят");
            }
        }
        if (user.getNickname().isEmpty()) {
            throw new InvalidNickException("Ник не может быть пустым.");
        }
        if (user.getBirthdate() == null) {
            throw new InvalidEmailException("Дата рождения должна быть заполнена.");
        }
        repository.addUser(user);
    }


    public User findUserByEmail(String email) {
        return repository.findUserByEmail(email);
    }

    public User findUserByNick(String nick) {
        return repository.findUserByNick(nick);
    }
}
