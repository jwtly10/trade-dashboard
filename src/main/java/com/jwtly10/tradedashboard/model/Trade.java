package com.jwtly10.tradedashboard.model;

import java.sql.Timestamp;

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

    public Trade(int ticketID, int accountID, String tradeType, String symbol, double size, double price, double sl, double tp, double swap, double profit, Timestamp opened, Timestamp closed, Timestamp created, String outcome) {
        this.ticketID = ticketID;
        this.accountID = accountID;
        this.tradeType = tradeType;
        this.symbol = symbol;
        this.size = size;
        this.price = price;
        this.sl = sl;
        this.tp = tp;
        this.swap = swap;
        this.profit = profit;
        this.opened = opened;
        this.closed = closed;
        this.created = created;
        this.outcome = outcome;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSl() {
        return sl;
    }

    public void setSl(double sl) {
        this.sl = sl;
    }

    public double getTp() {
        return tp;
    }

    public void setTp(double tp) {
        this.tp = tp;
    }

    public double getSwap() {
        return swap;
    }

    public void setSwap(double swap) {
        this.swap = swap;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public Timestamp getOpened() {
        return opened;
    }

    public void setOpened(Timestamp opened) {
        this.opened = opened;
    }

    public Timestamp getClosed() {
        return closed;
    }

    public void setClosed(Timestamp closed) {
        this.closed = closed;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "ticketID=" + ticketID +
                ", accountID=" + accountID +
                ", tradeType='" + tradeType + '\'' +
                ", symbol='" + symbol + '\'' +
                ", size=" + size +
                ", price=" + price +
                ", sl=" + sl +
                ", tp=" + tp +
                ", swap=" + swap +
                ", profit=" + profit +
                ", opened=" + opened +
                ", closed=" + closed +
                ", created=" + created +
                ", outcome='" + outcome + '\'' +
                '}';
    }
}
