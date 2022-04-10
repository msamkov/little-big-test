package com.example.littlebigtest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Jacksonized
@Builder
@Getter
public class TestAddDto {

    @NotBlank
    private final String name;

    @NotBlank
    private final String description;

    @NotNull
    private final Integer requiredAnswers;

//    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
//    public TestAddDto(@JsonProperty("name") final String name,
//                      @JsonProperty("description") final String description,
//                      @JsonProperty("requiredAnswers") final Integer requiredAnswers) {
//        this.name = name;
//        this.description = description;
//        this.requiredAnswers = requiredAnswers;
//    }

}
