package com.jwtly10.tradedashboard.api;

import com.jwtly10.tradedashboard.dto.TradeDeleteDTO;
import com.jwtly10.tradedashboard.model.Trade;
import com.jwtly10.tradedashboard.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/trade")
@RestController
@CrossOrigin(origins = {"http://localhost:5173/", "https://trade-dashboard.netlify.app/"})
public class TradeController {

    private final TradeService tradeService;

    @Autowired
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping("{accountID}")
    public List<Trade> getTrades(@PathVariable("accountID") int accountID) {
        return tradeService.getTradesByAccount(accountID);
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> deleteTrade(@RequestBody TradeDeleteDTO body) {
        int res = tradeService.deleteTrade(body.getTicketID(), body.getAccountID());
        return new ResponseEntity<>(res + " rows deleted.", HttpStatus.OK);
    }
}
