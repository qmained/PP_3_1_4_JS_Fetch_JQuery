package ru.kata.spring.boot_security.demo.exceptions;

import lombok.Data;

@Data
public class UserIncorrectData {
    private String data;

    public UserIncorrectData(String data) {
        this.data = data;
    }
}
