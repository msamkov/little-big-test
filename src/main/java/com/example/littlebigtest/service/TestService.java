package com.example.littlebigtest.service;

import com.example.littlebigtest.dto.TestAddDto;
import com.example.littlebigtest.dto.TestDto;
import com.example.littlebigtest.dto.TestListDto;
import com.example.littlebigtest.dto.TestUpdDto;
import com.example.littlebigtest.exception.TestNotFoundException;
import com.example.littlebigtest.mapper.TestMapper;
import com.example.littlebigtest.model.TestEntity;
import com.example.littlebigtest.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private final TestMapper testMapper;

    public TestDto addTest(final TestAddDto testAddDto) {
        final TestEntity newTestEntity = testMapper.toTestEntity(testAddDto);
        final TestEntity addTestEntity = testRepository.save(newTestEntity);
        return testMapper.toTestDto(addTestEntity);
    }

    @Transactional
    public void delete(final long id) {
        final TestEntity testEntity = testRepository.findById(id)
                .orElseThrow(() -> new TestNotFoundException(id));
        testRepository.deleteById(testEntity.getId());
    }

    public TestListDto list() {
        return testMapper.toTestListDto(testRepository.findAll());
    }

    public TestDto findById(final long id) {
        return testRepository.findById(id)
                .map(testMapper::toTestDto)
                .orElseThrow(() -> new TestNotFoundException(id));
    }

    @Transactional
    public TestDto update(final long id, final TestUpdDto testUpdDto) {
        final TestEntity testEntity = testRepository.findById(id)
                .orElseThrow(() -> new TestNotFoundException(id));
        testEntity.setName(testUpdDto.getName());
        testEntity.setDescription(testUpdDto.getDescription());
        testEntity.setRequiredAnswers(testUpdDto.getRequiredAnswers());
        return testMapper.toTestDto(testRepository.save(testEntity));
    }

}
