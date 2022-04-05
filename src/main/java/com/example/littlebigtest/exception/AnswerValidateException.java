package com.example.littlebigtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AnswerValidateException extends RuntimeException {

    public AnswerValidateException(final long answerId, final long questionId) {
        super(String.format("Answer with id %s not found in Question with id %s", answerId, questionId)) ;
    }

}
