package com.jwtly10.tradedashboard.dao;

import com.jwtly10.tradedashboard.model.Trade;

import java.util.List;
import java.util.Optional;

public interface TradeDAO<T> {

    List<T> list();

    void create(T t);

    List<T> get(int id);

    Optional<Trade> get(int id1, int id2);

    int update(T t, int id);

    int delete(int id);

    int delete(int id1, int id2);

}
