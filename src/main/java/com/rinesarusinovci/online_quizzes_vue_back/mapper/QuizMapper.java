package com.rinesarusinovci.online_quizzes_vue_back.mapper;



import com.rinesarusinovci.online_quizzes_vue_back.dto.QuizDto;
import com.rinesarusinovci.online_quizzes_vue_back.entities.Question;
import com.rinesarusinovci.online_quizzes_vue_back.entities.Quiz;
import com.rinesarusinovci.online_quizzes_vue_back.entities.Result;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Primary;

import java.util.List;


@Primary
@Mapper(componentModel = "spring")
public interface QuizMapper extends BaseMapper<Quiz, QuizDto> {
    QuizMapper INSTANCE = Mappers.getMapper(QuizMapper.class);

    @Override
    @Mapping(source = "createdBy", target = "createdBy.id")
    @Mapping(source = "questions", target = "questions")
//    @Mapping(source = "report", target = "report.id")
//    @Mapping(source = "results", target = "results")
    Quiz toEntity(QuizDto quizDto);

    @Override
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "questions", target = "questions")
//    @Mapping(source = "report.id", target = "report")
//    @Mapping(source = "results", target = "results")
    QuizDto toDto(Quiz quiz);

    @Override

    List<Quiz> toEntities(List<QuizDto> quizDtos);

    @Override

    List<QuizDto> toDtos(List<Quiz> quizzes);

    default List<Long> mapQuestionsToIds(List<Question> questions) {
        if (questions == null) return null;
        return questions.stream()
                .map(Question::getId)
                .toList();
    }

    default List<Question> mapIdsToQuestions(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream()
                .map(id -> {
                    Question question = new Question();
                    question.setId(id);
                    return question;
                })
                .toList();
    }

    default List<Long> mapResultsToIds(List<Result> results) {
        if (results == null) return null;
        return results.stream()
                .map(Result::getId)
                .toList();
    }

    default List<Result> mapIdsToResults(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream()
                .map(id -> {
                    Result result = new Result();
                    result.setId(id);
                    return result;
                })
                .toList();
    }

}
