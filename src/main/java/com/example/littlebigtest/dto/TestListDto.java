package com.example.littlebigtest.dto;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;


@Getter
public class TestListDto {

    private final List<TestDto> tests = new ArrayList<>();

    public TestListDto(final List<TestDto> testDtos) {
        tests.addAll(testDtos);
    }
}
