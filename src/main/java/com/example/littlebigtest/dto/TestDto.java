package com.example.littlebigtest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Getter
public class TestDto {

    @JsonProperty(value = "id")
    private final Long id;

    @JsonProperty(value = "name")
    private final String name;

    @JsonProperty(value = "description")
    private final String description;

    @JsonProperty(value = "requiredAnswers")
    private final Integer requiredAnswers;

//    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
//    public TestDto(@JsonProperty(value = "id") final Long id,
//                   @JsonProperty("name") final String name,
//                   @JsonProperty("description") final String description,
//                   @JsonProperty("requiredAnswers") final Integer requiredAnswers) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.requiredAnswers = requiredAnswers;
//    }
}
