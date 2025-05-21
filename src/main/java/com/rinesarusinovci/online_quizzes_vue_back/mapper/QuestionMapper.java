package com.rinesarusinovci.online_quizzes_vue_back.mapper;



import com.rinesarusinovci.online_quizzes_vue_back.dto.QuestionDto;
import com.rinesarusinovci.online_quizzes_vue_back.entities.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface QuestionMapper extends BaseMapper<Question, QuestionDto> {



    @Override
    @Mapping(source = "quiz.id", target = "quizId")

    QuestionDto toDto(Question question);

    @Override
    @Mapping(source = "quizId", target = "quiz.id")

    Question toEntity(QuestionDto questionDto);

    @Override
    List<QuestionDto> toDtos(List<Question> questions);

    @Override
    List<Question> toEntities(List<QuestionDto> questionDtos);


}
