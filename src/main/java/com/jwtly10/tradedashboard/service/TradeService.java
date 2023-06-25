package com.jwtly10.tradedashboard.service;

import com.jwtly10.tradedashboard.dao.TradeDAOImpl;
import com.jwtly10.tradedashboard.model.Trade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {
    private final TradeDAOImpl tradeDAOImpl;

    public TradeService(TradeDAOImpl tradeDAOImpl) {
        this.tradeDAOImpl = tradeDAOImpl;
    }

    public List<Trade> getTradesByAccount(int accountID){
        return tradeDAOImpl.get(accountID);
    }
}
