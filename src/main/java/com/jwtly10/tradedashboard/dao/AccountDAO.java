package com.jwtly10.tradedashboard.dao;


import java.util.List;
import java.util.Optional;

public interface AccountDAO<T> {

    List<T> list();

    void create(T t);

    Optional<T> get(int id);

    int update(T t, int id);

    int delete(int id);

}
