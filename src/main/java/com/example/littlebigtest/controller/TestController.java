package com.example.littlebigtest.controller;

import com.example.littlebigtest.dto.TestAddDto;
import com.example.littlebigtest.dto.TestDto;
import com.example.littlebigtest.dto.TestListDto;
import com.example.littlebigtest.dto.TestUpdDto;
import com.example.littlebigtest.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
public class TestController {

    private final TestService testService;

    @PostMapping(value = "/api/v1/test/add")
    public TestDto add(@Valid @RequestBody final TestAddDto testAddDto) {
        return testService.addTest(testAddDto);
    }

    @DeleteMapping(value = "/api/v1/test/delete/{id}")
    public void delete(@PathVariable("id") final long id) {
        testService.delete(id);
    }

    @GetMapping(value = "/api/v1/test/list")
    public TestListDto list() {
        return testService.list();
    }

    @GetMapping(value = "/api/v1/test/show/{id}")
    public TestDto show(@PathVariable("id") final long id) {
        return testService.findById(id);
    }

    @PutMapping(value = "/api/v1/test/update/{id}")
    public TestDto update(@PathVariable("id") final long id,
                                  @Valid @RequestBody final TestUpdDto test) {
        return testService.update(id, test);
    }
}
