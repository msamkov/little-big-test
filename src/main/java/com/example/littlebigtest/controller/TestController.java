package com.example.littlebigtest.controller;

import com.example.littlebigtest.dto.TestAddDto;
import com.example.littlebigtest.dto.TestDto;
import com.example.littlebigtest.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final TestService testService;

    @PostMapping(value = "/api/v1/test/add")
    public TestDto add(@Valid @RequestBody final TestAddDto testAddDto) {
        return testService.addTest(testAddDto);
    }

    // delete
    @DeleteMapping(value = "/api/v1/test/delete/{id}")
    public void delete(@PathVariable("id") final Integer id) {
        testService.delete(id);
    }

    // list

    // show

    // update
}
