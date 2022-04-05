package com.example.littlebigtest.service;

import com.example.littlebigtest.dto.AnswerAddDto;
import com.example.littlebigtest.dto.AnswerDto;
import com.example.littlebigtest.dto.QuestionAddDto;
import com.example.littlebigtest.dto.QuestionDto;
import com.example.littlebigtest.dto.QuestionListDto;
import com.example.littlebigtest.dto.QuestionUpdDto;
import com.example.littlebigtest.exception.AnswerValidateException;
import com.example.littlebigtest.exception.QuestionNotFoundException;
import com.example.littlebigtest.exception.TestNotFoundException;
import com.example.littlebigtest.model.AnswerEntity;
import com.example.littlebigtest.model.QuestionEntity;
import com.example.littlebigtest.model.TestEntity;
import com.example.littlebigtest.repository.QuestionRepository;
import com.example.littlebigtest.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public QuestionDto addQuestion(final QuestionAddDto questionAddDto) {
        final TestEntity testEntity = testRepository.findById(questionAddDto.getTestId())
                .orElseThrow(() -> new TestNotFoundException(questionAddDto.getTestId()));
        final QuestionEntity newQuestionEntity = toQuestionEntity(questionAddDto, testEntity);
        final QuestionEntity addQuestionEntity = questionRepository.save(newQuestionEntity);
        return toQuestionDto(addQuestionEntity);
    }

    private QuestionDto toQuestionDto(final QuestionEntity questionEntity) {
        return new QuestionDto()
                .setTestId(questionEntity.getTest().getId())
                .setId(questionEntity.getId())
                .setQuestion(questionEntity.getQuestion())
                .setAnswers(
                        questionEntity.getAnswers()
                                .stream()
                                .map(this::toAnswerDto)
                                .collect(Collectors.toList())
                );
    }

    private AnswerDto toAnswerDto(final AnswerEntity answerEntity) {
        return new AnswerDto()
                .setId(answerEntity.getId())
                .setAnswer(answerEntity.getAnswer())
                .setCorrect(answerEntity.isCorrect());
    }

    private QuestionEntity toQuestionEntity(final QuestionAddDto questionAddDto, final TestEntity testEntity) {
        final QuestionEntity questionEntity = new QuestionEntity();
        questionEntity
                .setTest(testEntity)
                .setQuestion(questionAddDto.getQuestion())
                .setAnswers(questionAddDto.getAnswers()
                        .stream()
                        .map(answerAddDto -> toAnswerEntity(answerAddDto, questionEntity))
                        .collect(Collectors.toList())
                );
        return questionEntity;
    }

    private AnswerEntity toAnswerEntity(final AnswerAddDto answerAddDto, final QuestionEntity questionEntity) {
        return new AnswerEntity()
                .setAnswer(answerAddDto.getAnswer())
                .setCorrect(answerAddDto.isCorrect())
                .setQuestion(questionEntity);
    }

    // delete
    @Transactional
    public void delete(final long id) {
        final QuestionEntity questionEntity = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
        questionRepository.deleteById(questionEntity.getId());
    }


    // list by testId
    @Transactional
    public QuestionListDto listByTestId(final long testId) {
        final TestEntity testEntity = testRepository.findById(testId)
                .orElseThrow(() -> new TestNotFoundException(testId));
        final List<QuestionEntity> questions = questionRepository.findByTestId(testEntity.getId());
        return new QuestionListDto(questions
                .stream()
                .map(this::toQuestionDto)
                .collect(Collectors.toList()));
    }

    // find by id
    @Transactional
    public QuestionDto findById(final long id) {
        return questionRepository.findById(id)
                .map(this::toQuestionDto)
                .orElseThrow(() -> new QuestionNotFoundException(id));
    }

    // update
    @Transactional
    public QuestionDto update(final long id, final QuestionUpdDto questionUpdDto) {
        final QuestionEntity questionEntity = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
        questionEntity.setQuestion(questionUpdDto.getQuestion());
        updateAnswers(questionEntity, questionUpdDto.getAnswers());
        questionRepository.save(questionEntity);
        return toQuestionDto(questionEntity);
    }

    private void updateAnswers(final QuestionEntity questionEntity, final List<AnswerDto> answers) {

        // delete
        final List<Long> notDelIds = answers.stream()
                .map(AnswerDto::getId)
                .collect(Collectors.toList());

        questionEntity.getAnswers()
                .removeIf(answerEntity -> !notDelIds.contains(answerEntity.getId()));

        // update
        final List<AnswerDto> answersForUpd = answers.stream()
                .filter(answerDto -> answerDto.getId() != null)
                .collect(Collectors.toList());

        for (final AnswerDto answerDto: answersForUpd) {
            final AnswerEntity answerUpd = questionEntity.getAnswers()
                    .stream()
                    .filter(answerEntity1 -> answerEntity1.getId().equals(answerDto.getId()))
                    .findFirst()
                    .orElseThrow(() -> new AnswerValidateException(answerDto.getId(), questionEntity.getId()));

            answerUpd.setAnswer(answerDto.getAnswer());
            answerUpd.setCorrect(answerDto.isCorrect());
        }

        // insert
        final List<AnswerEntity> insertAnswer = answers
                .stream()
                .filter(answerEntity1 -> answerEntity1.getId() == null)
                .map(answerDto -> toAnswerEntity(answerDto, questionEntity))
                .collect(Collectors.toList());
        questionEntity.getAnswers().addAll(insertAnswer);

    }

    private AnswerEntity toAnswerEntity(final AnswerDto answerDto, final QuestionEntity questionEntity) {
        return new AnswerEntity()
                .setAnswer(answerDto.getAnswer())
                .setCorrect(answerDto.isCorrect())
                .setQuestion(questionEntity);
    }

}
