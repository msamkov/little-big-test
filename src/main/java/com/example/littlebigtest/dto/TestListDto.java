package com.example.littlebigtest.dto;

import lombok.Getter;
import java.util.Collections;
import java.util.List;

@Getter
public class TestListDto {

    private final List<TestDto> tests;

    public TestListDto(final List<TestDto> testDtos) {
        tests = Collections.unmodifiableList(testDtos);
    }
}
