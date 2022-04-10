package com.example.littlebigtest.service;

import com.example.littlebigtest.AbstractIntegrationTest;
import com.example.littlebigtest.dto.AnswerAddDto;
import com.example.littlebigtest.dto.AnswerDto;
import com.example.littlebigtest.dto.QuestionAddDto;
import com.example.littlebigtest.dto.QuestionDto;
import com.example.littlebigtest.dto.QuestionUpdDto;
import com.example.littlebigtest.dto.TestAddDto;
import com.example.littlebigtest.dto.TestDto;
import com.example.littlebigtest.fixture.TestAddDtoFixture;
import com.example.littlebigtest.model.TestEntity;
import com.example.littlebigtest.repository.QuestionRepository;
import com.example.littlebigtest.repository.TestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionServiceTest extends AbstractIntegrationTest {

    @SpyBean
    private QuestionService questionService;

    @SpyBean
    private QuestionRepository questionRepository;

    @SpyBean
    private TestRepository testRepository;

    @SpyBean
    private TestService testService;

    @BeforeEach
    void cleanQuestions() {
        questionRepository.deleteAll();
        testRepository.deleteAll();
    }

    @Test
    void questionAdd() {
        // arrange
        final TestDto testDto = testService.addTest(TestAddDtoFixture.createDefault());
        final QuestionAddDto questionAddDto = new QuestionAddDto()
                .setTestId(testDto.getId())
                .setQuestion("В каком случае водитель совершит вынужденную остановку?")
                .setAnswers(
                        Arrays.asList(
                                new AnswerAddDto()
                                        .setAnswer("Остановившись непосредственно перед пешеходным переходом," +
                                                " чтобы уступить дорогу пешеходу.")
                                        .setCorrect(false),
                                new AnswerAddDto()
                                        .setAnswer("Остановившись на проезжей части из-за технической неисправности" +
                                                " транспортного средства.")
                                        .setCorrect(true),
                                new AnswerAddDto()
                                        .setAnswer("В обоих перечисленных случаях.")
                                        .setCorrect(false)
                        )

                );
        final int expectedAnswerSize = 3;

        // act
        final QuestionDto actualQuestionDto = questionService.addQuestion(questionAddDto);

        // assert
        Assertions.assertNotNull(actualQuestionDto.getId());
        Assertions.assertEquals(questionAddDto.getQuestion(), actualQuestionDto.getQuestion());
        Assertions.assertEquals(expectedAnswerSize, actualQuestionDto.getAnswers().size());

        for( final AnswerDto actualAnswer: actualQuestionDto.getAnswers()) {
            final AnswerAddDto expectedAnswerAdd = questionAddDto.getAnswers().stream()
                            .filter(answerAddDto -> answerAddDto.getAnswer().equals(actualAnswer.getAnswer()))
                            .findFirst()
                            .orElse(null);
            Assertions.assertNotNull(expectedAnswerAdd);
            Assertions.assertEquals(expectedAnswerAdd.getAnswer(), actualAnswer.getAnswer());
            Assertions.assertEquals(expectedAnswerAdd.isCorrect(), actualAnswer.isCorrect());
        }
    }

    // delete
    @Test
    void questionDelete() {
        // arrange
        final TestDto testDto = testService.addTest(TestAddDtoFixture.createDefault());
        final QuestionAddDto questionAddDto = new QuestionAddDto()
                .setTestId(testDto.getId())
                .setQuestion("В каком случае водитель совершит вынужденную остановку?")
                .setAnswers(
                        Arrays.asList(
                                new AnswerAddDto()
                                        .setAnswer("Остановившись непосредственно перед пешеходным переходом," +
                                                " чтобы уступить дорогу пешеходу.")
                                        .setCorrect(false),
                                new AnswerAddDto()
                                        .setAnswer("Остановившись на проезжей части из-за технической неисправности" +
                                                " транспортного средства.")
                                        .setCorrect(true),
                                new AnswerAddDto()
                                        .setAnswer("В обоих перечисленных случаях.")
                                        .setCorrect(false)
                        )

                );
        final QuestionDto questionDto = questionService.addQuestion(questionAddDto);

        // act
        questionService.delete(questionDto.getId());

        // assert
        Assertions.assertEquals(0, questionRepository.findAll().size());
    }

    @Test
    void questionListFindByTestId() {
        // arrange
        questionAdd();
        questionAdd();
        final int expectedSize = 2;
        final List<Long> testIds = testRepository.findAll()
                .stream()
                .map(TestEntity::getId)
                .collect(Collectors.toList());


        // act
        final long actualSize = testIds.stream()
                .mapToLong(testId -> questionService.listByTestId(testId).getQuestions().size())
                .sum();

        // assert
        Assertions.assertEquals(expectedSize, actualSize);
    }



    // find by test id
    @Test
    void questionFindById() {
        // arrange
        final TestDto testDto = testService.addTest(TestAddDtoFixture.createDefault());
        final QuestionAddDto questionAddDto = new QuestionAddDto()
                .setTestId(testDto.getId())
                .setQuestion("В каком случае водитель совершит вынужденную остановку?")
                .setAnswers(
                        Arrays.asList(
                                new AnswerAddDto()
                                        .setAnswer("Остановившись непосредственно перед пешеходным переходом," +
                                                " чтобы уступить дорогу пешеходу.")
                                        .setCorrect(false),
                                new AnswerAddDto()
                                        .setAnswer("Остановившись на проезжей части из-за технической неисправности" +
                                                " транспортного средства.")
                                        .setCorrect(true),
                                new AnswerAddDto()
                                        .setAnswer("В обоих перечисленных случаях.")
                                        .setCorrect(false)
                        )

                );
        final QuestionDto questionDto = questionService.addQuestion(questionAddDto);
        final int expectedAnswerSize = 3;

        // act
        final QuestionDto actualQuestionDto = questionService.findById(questionDto.getId());

        // assert
        Assertions.assertNotNull(actualQuestionDto.getId());
        Assertions.assertEquals(questionAddDto.getQuestion(), actualQuestionDto.getQuestion());
        Assertions.assertEquals(expectedAnswerSize, actualQuestionDto.getAnswers().size());

        for( final AnswerDto actualAnswer: actualQuestionDto.getAnswers()) {
            final AnswerAddDto expectedAnswerAdd = questionAddDto.getAnswers().stream()
                    .filter(answerAddDto -> answerAddDto.getAnswer().equals(actualAnswer.getAnswer()))
                    .findFirst()
                    .orElse(null);
            Assertions.assertNotNull(expectedAnswerAdd);
            Assertions.assertEquals(expectedAnswerAdd.getAnswer(), actualAnswer.getAnswer());
            Assertions.assertEquals(expectedAnswerAdd.isCorrect(), actualAnswer.isCorrect());
        }
    }


    // update
    @Test
    void questionUpdate() {
        // arrange
        final TestDto testDto = testService.addTest(TestAddDtoFixture.createDefault());
        final QuestionAddDto questionAddDto = new QuestionAddDto()
                .setTestId(testDto.getId())
                .setQuestion("В каком случае водитель совершит вынужденную остановку?")
                .setAnswers(
                        Arrays.asList(
                                new AnswerAddDto()
                                        .setAnswer("Остановившись непосредственно перед пешеходным переходом," +
                                                " чтобы уступить дорогу пешеходу.")
                                        .setCorrect(false),
                                new AnswerAddDto()
                                        .setAnswer("Остановившись на проезжей части из-за технической неисправности" +
                                                " транспортного средства.")
                                        .setCorrect(true),
                                new AnswerAddDto()
                                        .setAnswer("В обоих перечисленных случаях.")
                                        .setCorrect(false)
                        )

                );
        final int expectedAnswerSize = 2;
        final QuestionDto addQuestionDto = questionService.addQuestion(questionAddDto);
        final QuestionUpdDto expectedQuestionUpdDto = new QuestionUpdDto()
                .setQuestion("В каком случае ВОДИТЕЛЬ 1001 совершит вынужденную остановку?")
                .setAnswers(
                        Arrays.asList(
                        new AnswerDto()
                                .setId(addQuestionDto.getAnswers()
                                        .stream()
                                        .filter(answerDto -> answerDto.getAnswer().equals(
                                                "Остановившись непосредственно перед пешеходным переходом," +
                                                " чтобы уступить дорогу пешеходу."))
                                        .map(AnswerDto::getId)
                                        .findFirst()
                                        .orElse(null))
                                .setAnswer("Остановившись 1002 непосредственно перед пешеходным переходом," +
                                        " чтобы уступить дорогу пешеходу.")
                                .setCorrect(true),
                        new AnswerDto()
                                .setId(addQuestionDto.getAnswers()
                                        .stream()
                                        .filter(answerDto -> answerDto.getAnswer().equals(
                                                "Остановившись на проезжей части из-за технической неисправности" +
                                                " транспортного средства."))
                                        .map(AnswerDto::getId)
                                        .findFirst()
                                        .orElse(null))
                                .setAnswer("Остановившись на проезжей части из-за технической неисправности" +
                                        " транспортного средства.")
                                .setCorrect(false)
                        )
        );

        // act
        final QuestionDto actualQuestionUpd = questionService.update(addQuestionDto.getId(), expectedQuestionUpdDto);

        // assert
        Assertions.assertEquals(expectedQuestionUpdDto.getQuestion(), actualQuestionUpd.getQuestion());
        Assertions.assertEquals(expectedAnswerSize, actualQuestionUpd.getAnswers().size());

        for( final AnswerDto actualAnswer: actualQuestionUpd.getAnswers()) {
            final AnswerDto expectedAnswer = expectedQuestionUpdDto.getAnswers().stream()
                    .filter(answerAddDto -> answerAddDto.getAnswer().equals(actualAnswer.getAnswer()))
                    .findFirst()
                    .orElse(null);
            Assertions.assertNotNull(expectedAnswer);
            Assertions.assertEquals(expectedAnswer.getId(), actualAnswer.getId());
            Assertions.assertEquals(expectedAnswer.getAnswer(), actualAnswer.getAnswer());
            Assertions.assertEquals(expectedAnswer.isCorrect(), actualAnswer.isCorrect());
        }
    }

}
