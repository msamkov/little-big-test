package com.example.littlebigtest.service;

import com.example.littlebigtest.AbstractIntegrationTest;
import com.example.littlebigtest.dto.TestAddDto;
import com.example.littlebigtest.dto.TestDto;
import com.example.littlebigtest.dto.TestListDto;
import com.example.littlebigtest.dto.TestUpdDto;
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
        final String name = "Name 1001";
        final String description = "Desc 1002";
        final TestDto excpectedTest = new TestDto()
                .setName(name)
                .setDescription(description);
        final TestAddDto testAddDto = new TestAddDto(name, description);

        // act
        final TestDto actualTest = testService.addTest(testAddDto);

        // assert
        Assertions.assertEquals(excpectedTest.getName(), actualTest.getName());
        Assertions.assertEquals(excpectedTest.getDescription(), actualTest.getDescription());
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
        final TestDto test = testService.addTest(new TestAddDto("Name 1001", "Desc 1002"));
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
        final TestDto expectedTest = testService.addTest(new TestAddDto("Name 1001", "Desc 1002"));

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
        final TestDto test = testService.addTest(new TestAddDto("Name 1001", "Desc 1002"));
        final TestDto excpectedTest = testService.addTest(new TestAddDto("Name 1002", "Desc 1002"));
        final TestDto addTest = testService.findById(test.getId());

        // act
        final TestUpdDto testUpdDto = new TestUpdDto()
                .setName(excpectedTest.getName())
                .setDescription(excpectedTest.getDescription());
        final TestDto actualTest = testService.update(addTest.getId(), testUpdDto);

        // assert
        Assertions.assertEquals(excpectedTest.getName(), actualTest.getName());
        Assertions.assertEquals(excpectedTest.getDescription(), actualTest.getDescription());
    }

}
