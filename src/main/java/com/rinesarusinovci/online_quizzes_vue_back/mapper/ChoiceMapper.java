package com.rinesarusinovci.online_quizzes_vue_back.mapper;

import com.rinesarusinovci.online_quizzes_vue_back.entities.Choice;
import com.rinesarusinovci.online_quizzes_vue_back.dto.ChoiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ChoiceMapper extends BaseMapper<Choice, ChoiceDto> {
    ChoiceMapper INSTANCE = Mappers.getMapper(ChoiceMapper.class);



    @Override
    @Mapping(source = "question.id", target = "questionId")
    ChoiceDto toDto(Choice choice);

    @Override
    @Mapping(source = "questionId", target = "question.id")
    Choice toEntity(ChoiceDto choiceDto);

    @Override
    List<ChoiceDto> toDtos(List<Choice> choices);

    @Override
    List<Choice> toEntities(List<ChoiceDto> choiceDtos);





}
