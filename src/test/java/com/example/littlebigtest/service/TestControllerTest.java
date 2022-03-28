package com.example.littlebigtest.service;

import com.example.littlebigtest.AbstractIntegrationTest;
import com.example.littlebigtest.controller.TestController;
import com.example.littlebigtest.dto.TestAddDto;
import com.example.littlebigtest.dto.TestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;

public class TestControllerTest extends AbstractIntegrationTest {

    @SpyBean
    private TestController testController;

    @Test
    public void testAdd() {
        // arrange
        final String name = "Name 1001";
        final String description = "Desc 1002";
        final TestDto excpectedTest = new TestDto()
                .setName(name)
                .setDescription(description);
        final TestAddDto testAddDto = new TestAddDto(name, description);

        // act
        final TestDto actualTest = testController.add(testAddDto);

        // assert
        Assertions.assertEquals(excpectedTest.getName(), actualTest.getName());
        Assertions.assertEquals(excpectedTest.getDescription(), actualTest.getDescription());
    }

}
