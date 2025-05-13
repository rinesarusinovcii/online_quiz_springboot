package com.rinesarusinovci.online_quizzes_vue_back.mapper;


import com.rinesarusinovci.online_quizzes_vue_back.dto.ResultDto;
import com.rinesarusinovci.online_quizzes_vue_back.entities.Result;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")

public interface ResultMapper extends BaseMapper<Result, ResultDto> {
    ResultMapper INSTANCE = Mappers.getMapper(ResultMapper.class);

    @Override
//    @Mapping(source = "quiz.id", target = "quizId")
    ResultDto toDto(Result result);

    @Override
//    @Mapping(source = "quizId", target = "quiz.id")
    Result toEntity(ResultDto resultDto);

    @Override
    List<ResultDto> toDtos(List<Result> results);

    @Override
    List<Result> toEntities(List<ResultDto> resultDtos);


}
