package com.example.littlebigtest.service;

import com.example.littlebigtest.dto.QuestionAddDto;
import com.example.littlebigtest.dto.QuestionDto;
import com.example.littlebigtest.mapper.QuestionMapper;
import com.example.littlebigtest.model.QuestionEntity;
import com.example.littlebigtest.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public QuestionDto addQuestion(final QuestionAddDto questionAddDto) {
        final QuestionEntity newQuestionEntity = questionMapper.toQuestionEntity(questionAddDto);
        final QuestionEntity addQuestionEntity = questionRepository.save(newQuestionEntity);
        return questionMapper.toQuestionDto(addQuestionEntity);
    }

    // delete

    // list by testId

    // find by id

    // update

}
