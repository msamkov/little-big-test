package com.example.littlebigtest.controller;

import com.example.littlebigtest.dto.QuestionAddDto;
import com.example.littlebigtest.dto.QuestionDto;
import com.example.littlebigtest.dto.QuestionListDto;
import com.example.littlebigtest.dto.QuestionUpdDto;
import com.example.littlebigtest.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    private final QuestionService questionService;

    // add
    @PostMapping(value = "/api/v1/question/add")
    public QuestionDto add(@Valid @RequestBody final QuestionAddDto questionAddDto) {
        return questionService.addQuestion(questionAddDto);
    }

    // delete
    @DeleteMapping(value = "/api/v1/question/delete/{id}")
    public void delete(@PathVariable("id") final long id) {
        questionService.delete(id);
    }

    // list by testId
    @GetMapping(value = "/api/v1/question/list/{testId}")
    public QuestionListDto list(@PathVariable("testId") final long testId) {
        return questionService.listByTestId(testId);
    }

    // find by id
    @GetMapping(value = "/api/v1/question/show/{id}")
    public QuestionDto show(@PathVariable("id") final long id) {
        return questionService.findById(id);
    }

    // update
    @PutMapping(value = "/api/v1/question/update/{id}")
    public QuestionDto update(@PathVariable("id") final long id,
                          @Valid @RequestBody final QuestionUpdDto questionUpdDto) {
        return questionService.update(id, questionUpdDto);
    }



}
