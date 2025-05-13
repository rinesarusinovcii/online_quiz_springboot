package com.rinesarusinovci.online_quizzes_vue_back.mapper;



import com.rinesarusinovci.online_quizzes_vue_back.dto.ReportDto;
import com.rinesarusinovci.online_quizzes_vue_back.entities.Report;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ReportMapper extends BaseMapper<Report, ReportDto> {

    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    @Override
//    @Mapping(source = "quizId", target = "quiz.id")
    List<Report> toEntities(List<ReportDto> reportDtos);

    @Override
//    @Mapping(source = "quiz.id", target = "quizId")
    List<ReportDto> toDtos(List<Report> reports);

    @Override
//    @Mapping(source = "quizId", target = "quiz.id")
    Report toEntity(ReportDto reportDto);

    @Override
//    @Mapping(source = "quiz.id", target = "quizId")
    ReportDto toDto(Report report);



}
