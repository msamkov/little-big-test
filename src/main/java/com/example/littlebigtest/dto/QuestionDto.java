package com.example.littlebigtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class QuestionDto {

    private Long testId;

    private Long id;

    private String question;

    List<AnswerDto> answers = new ArrayList<>();

}
