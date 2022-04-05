package com.example.littlebigtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class QuestionAddDto {

    @NotNull
    private Long testId;

    @NotBlank
    private String question;

    List<AnswerAddDto> answers = new ArrayList<>();

}
