package com.rinesarusinovci.online_quizzes_vue_back.mapper;



import java.util.List;

public interface BaseMapper<TEntity, TDto> {
    TEntity toEntity(TDto dto);

    TDto toDto(TEntity entity);

    List<TEntity> toEntities(List<TDto> dtos);

    List<TDto> toDtos(List<TEntity> entities);
}
