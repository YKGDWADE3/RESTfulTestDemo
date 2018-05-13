package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundEx extends RuntimeException{
    public UserNotFoundEx(long id) {
        super("could not find user '" + id + "'.");

    }
}
