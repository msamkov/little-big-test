package com.example.littlebigtest.fixture;

import com.example.littlebigtest.dto.TestAddDto;


public class TestAddDtoFixture {

    private static final String name = "Тесты ПДД";
    private static final String description = "тесты ПДД 2022";
    private static final int requiredAnswers = 2;

    public static TestAddDto createDefault() {
        return TestAddDto.builder()
                .name(name)
                .description(description)
                .requiredAnswers(requiredAnswers)
                .build();
    }

}
