package com.itstep.javafall2016.dao;

import com.itstep.javafall2016.entities.Passport;
import com.itstep.javafall2016.enums.Result;

import java.util.List;
import java.util.Optional;

/**
 * Created by strazhko on 18.07.2017.
 */
public interface GlobalDAO<T> {
    List<T> findAll();
    List<T> findAll(int pageSize, int pageNumber);
    void save(T entity);
    boolean update(T entity);
    Result delete(long id);
    Optional<T> findById(long id);

    default List<T> findAllExcept(List<Long> ids) {
        throw new UnsupportedOperationException();
    }
}
