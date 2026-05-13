package com.hu.huspring.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {

    T save(T entity);

    List<T> getAll();

    Optional<T> getById(ID id);

    boolean deleteById(ID id);

    T updateById(ID id, T entity);
}