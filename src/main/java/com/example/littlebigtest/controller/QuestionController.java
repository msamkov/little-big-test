package com.example.littlebigtest.controller;

import com.example.littlebigtest.dto.QuestionAddDto;
import com.example.littlebigtest.dto.QuestionDto;
import com.example.littlebigtest.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    private final QuestionService questionService;

    // add
    @PostMapping(value = "/api/v1/test/add")
    public QuestionDto add(@Valid @RequestBody final QuestionAddDto testAddDto) {
        return questionService.addQuestion(testAddDto);
    }



    // delete

    // list by testId

    // find by id

    // update



}
