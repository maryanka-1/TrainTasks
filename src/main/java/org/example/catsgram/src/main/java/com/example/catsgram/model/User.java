package com.example.catsgram.model;

import org.yaml.snakeyaml.Yaml;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    private String nickName;
    private LocalDateTime birthday;
    private String email;


    public User(String nickName, LocalDateTime birthday, String email) {
        this.nickName = nickName;
        this.birthday = birthday;
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
