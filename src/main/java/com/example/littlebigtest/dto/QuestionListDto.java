package com.example.littlebigtest.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class QuestionListDto {

    private final List<QuestionDto> questions = new ArrayList<>();

    public QuestionListDto(final List<QuestionDto> questionDtos) {
        questions.addAll(questionDtos);
    }

}
