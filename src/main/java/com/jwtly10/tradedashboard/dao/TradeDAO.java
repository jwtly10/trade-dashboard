package com.jwtly10.tradedashboard.dao;


import java.util.List;
import java.util.Optional;

public interface TradeDAO<T> {

    List<T> list();

    void create(T t);

    List<T> get(int id);

    Optional<T> get(int id1, int id2);

    int update(T t, int id);

    int delete(int id);

    int delete(int id1, int id2);

}
