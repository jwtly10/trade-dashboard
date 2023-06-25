package com.jwtly10.tradedashboard.api;

import com.jwtly10.tradedashboard.model.Trade;
import com.jwtly10.tradedashboard.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/v1/trade")
@RestController
public class TradeController {

    private final TradeService tradeService;
    @Autowired
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping(path = "{accountID}")
    public List<Trade> getTrades(@PathVariable("accountID") int accountID){
        return tradeService.getTradesByAccount(accountID);
    }
}
