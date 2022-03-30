package com.example.littlebigtest.mapper;

import com.example.littlebigtest.dto.QuestionAddDto;
import com.example.littlebigtest.dto.QuestionDto;
import com.example.littlebigtest.model.QuestionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionEntity toQuestionEntity(QuestionAddDto questionAddDto);
    QuestionDto toQuestionDto(QuestionEntity questionEntity);

}
