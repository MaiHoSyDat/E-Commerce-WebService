package com.example.case6.service;

import java.util.List;
import java.util.Optional;

public interface IGeneratedService<E, ID> {
    List<E> findAll();

    Optional<E> findById(ID id);

    void save(E e);

    void delete(ID id);
}
