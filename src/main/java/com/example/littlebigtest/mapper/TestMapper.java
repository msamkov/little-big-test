package com.example.littlebigtest.mapper;

import com.example.littlebigtest.dto.TestAddDto;
import com.example.littlebigtest.dto.TestDto;
import com.example.littlebigtest.model.TestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestMapper {

    TestEntity toTestEntity(TestAddDto testAddDto);
    TestDto toTestDto(TestEntity testEntity);
}
