package com.jwtly10.tradedashboard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TradeDeleteDTO {
    int ticketID;
    int accountID;

    public TradeDeleteDTO(@JsonProperty("ticketID") int ticketID, @JsonProperty("accountID") int accountID){
        this.ticketID = ticketID;
        this.accountID = accountID;
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
}
