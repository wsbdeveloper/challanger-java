package com.br.foodsave.backend.infrastructure.repository.dao;

import com.br.foodsave.backend.infrastructure.repository.PartnerEntity;

import java.util.List;
import java.util.Optional;

public interface PartnerDAO<T> {
    PartnerEntity create(T t);

    List<T> list();

    Optional<T> get(int id);

    boolean delete(int id);
}
