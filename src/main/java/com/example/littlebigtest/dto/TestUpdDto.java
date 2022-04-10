package com.example.littlebigtest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class TestUpdDto {

    @NotBlank
    private final String name;

    @NotBlank
    private final String description;

    @NotNull
    private final Integer requiredAnswers;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public TestUpdDto(@JsonProperty("name") final String name,
                      @JsonProperty("description") final String description,
                      @JsonProperty("requiredAnswers") final Integer requiredAnswers) {
        this.name = name;
        this.description = description;
        this.requiredAnswers = requiredAnswers;
    }

}
