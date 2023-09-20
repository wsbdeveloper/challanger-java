package com.br.foodsave.backend.infrastructure.repository.dao;

import java.util.List;
import java.util.Optional;

public interface PartnerDAO<T> {
    void create(T t);

    List<T> list();

    Optional<T> get(int id);

    void delete(int id);
}
