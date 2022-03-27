package com.example.littlebigtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TestNotFoundException extends RuntimeException {

    public TestNotFoundException(final long id) {
        super(String.format("Test with id %s not found", id)) ;

    }
}
