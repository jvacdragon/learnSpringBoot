package com.restapi.webservices.restfulwebservice.post;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PostForUserNotFoundException extends RuntimeException {
    public PostForUserNotFoundException(String message) {
        super(message);
    }
}
