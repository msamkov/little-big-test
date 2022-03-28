package com.example.littlebigtest.mapper;

import com.example.littlebigtest.dto.TestAddDto;
import com.example.littlebigtest.dto.TestDto;
import com.example.littlebigtest.dto.TestListDto;
import com.example.littlebigtest.model.TestEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TestMapper {

    TestEntity toTestEntity(TestAddDto testAddDto);
    TestDto toTestDto(TestEntity testEntity);

    default TestListDto toTestListDto(List<TestEntity> testEntities) {
        return new TestListDto(testEntities.stream()
                .map(this::toTestDto)
                .collect(Collectors.toList()));
    }
}
