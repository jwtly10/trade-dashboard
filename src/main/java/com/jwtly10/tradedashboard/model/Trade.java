package com.jwtly10.tradedashboard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Trade {
    int ticketID;
    int accountID;
    String tradeType;
    String symbol;
    double size;
    double price;
    double sl;
    double tp;
    double swap;
    double profit;
    Timestamp opened;
    Timestamp closed;
    Timestamp created;
    String outcome;

    public Trade() {
    }

}
