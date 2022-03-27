package com.example.littlebigtest.service;

import com.example.littlebigtest.dto.TestAddDto;
import com.example.littlebigtest.dto.TestDto;
import com.example.littlebigtest.mapper.TestMapper;
import com.example.littlebigtest.model.TestEntity;
import com.example.littlebigtest.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private final TestMapper testMapper;

    public TestDto addTest(TestAddDto testAddDto) {
        final TestEntity newTestEntity = testMapper.toTestEntity(testAddDto);
        final TestEntity addTestEntity = testRepository.save(newTestEntity);
        return testMapper.toTestDto(addTestEntity);
    }



}
