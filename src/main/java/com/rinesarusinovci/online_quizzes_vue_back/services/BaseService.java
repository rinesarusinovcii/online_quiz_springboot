package com.rinesarusinovci.online_quizzes_vue_back.services;

import java.util.List;

public interface BaseService<T,TId> {
    List<T> findAll();

    T findById(TId id);

    T add(T model);

    T modify(TId id, T model);

    void removeById(TId id);
}
