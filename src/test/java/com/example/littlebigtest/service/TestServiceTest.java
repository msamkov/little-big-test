package com.example.littlebigtest.service;

import com.example.littlebigtest.AbstractIntegrationTest;
import com.example.littlebigtest.dto.TestAddDto;
import com.example.littlebigtest.dto.TestDto;
import com.example.littlebigtest.dto.TestListDto;
import com.example.littlebigtest.dto.TestUpdDto;
import com.example.littlebigtest.fixture.TestAddDtoFixture;
import com.example.littlebigtest.repository.TestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;

public class TestServiceTest extends AbstractIntegrationTest {

    @SpyBean
    private TestService testService;

    @SpyBean
    private TestRepository testRepository;

    @BeforeEach
    void cleanTests() {
        testRepository.deleteAll();
    }

    @Test
    void add() {
        // arrange
        final TestAddDto testAddDto = TestAddDtoFixture.createDefault();
        final TestDto expectedTest = TestDto.builder()
                .name(testAddDto.getName())
                .description(testAddDto.getDescription())
                .requiredAnswers(testAddDto.getRequiredAnswers())
                .build();

        // act
        final TestDto actualTest = testService.addTest(testAddDto);

        // assert
        Assertions.assertEquals(expectedTest.getName(), actualTest.getName());
        Assertions.assertEquals(expectedTest.getDescription(), actualTest.getDescription());
        Assertions.assertEquals(expectedTest.getRequiredAnswers(), actualTest.getRequiredAnswers());
    }

    @Test
    void list() {
        // arrange
        add();
        add();
        final int expectedSize = 2;


        // act
        final TestListDto actualTests = testService.list();

        // assert
        Assertions.assertEquals(expectedSize, actualTests.getTests().size());
    }


    // delete
    @Test
    void delete() {
        // arrange
        final TestDto test = testService.addTest(TestAddDtoFixture.createDefault());
        final int expectedSize = 0;

        // act
        testService.delete(test.getId());

        // assert
        Assertions.assertEquals(expectedSize, testService.list().getTests().size());
    }


    // show
    @Test
    void show() {
        // arrange
        final TestDto expectedTest = testService.addTest(TestAddDtoFixture.createDefault());

        // act
        TestDto actualTest = testService.findById(expectedTest.getId());

        // assert
        Assertions.assertEquals(expectedTest.getId(), actualTest.getId());
        Assertions.assertEquals(expectedTest.getName(), actualTest.getName());
        Assertions.assertEquals(expectedTest.getDescription(), actualTest.getDescription());
    }


    @Test
    void update() {
        // arrange
        final TestDto testDto = testService.addTest(TestAddDtoFixture.createDefault());
        final TestDto expectedTest = TestDto.builder()
                .name("Тесты 1001")
                .description("Тесты 1002")
                .requiredAnswers(12)
                .build();
        final TestDto addTest = testService.findById(testDto.getId());

        // act
        final TestUpdDto testUpdDto = new TestUpdDto(expectedTest.getName(),
                expectedTest.getDescription(),
                expectedTest.getRequiredAnswers());


        final TestDto actualTest = testService.update(addTest.getId(), testUpdDto);

        // assert
        Assertions.assertEquals(expectedTest.getName(), actualTest.getName());
        Assertions.assertEquals(expectedTest.getDescription(), actualTest.getDescription());
        Assertions.assertEquals(expectedTest.getRequiredAnswers(), actualTest.getRequiredAnswers());
    }

}
