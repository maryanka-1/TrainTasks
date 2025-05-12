package com.example.catsgram.repository;

import com.example.catsgram.exceptions.UserNotFoundException;
import com.example.catsgram.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private final Map<String, User> listUsers = new HashMap<>();

    public Map<String, User> getListUsers() {
        return listUsers;
    }

    public User addUser(User user) {
        listUsers.put(user.getEmail(), user);
        return user;
    }

    public User findUserByEmail(String email) {
        if (email == null) {
            return null;
        }
        return listUsers.get(email);
    }

    public User findUserByNick(String nick) {
        if (nick == null) {
            return null;
        }
        return listUsers.values().stream()
                .filter(user -> user.getNickname().equals(nick))
                .findFirst()
                .orElseThrow(() ->new UserNotFoundException("Пользователь c ником " + nick + " не найден"));
    }
}
