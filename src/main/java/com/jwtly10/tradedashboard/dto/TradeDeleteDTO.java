package com.jwtly10.tradedashboard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeDeleteDTO {
    int ticketID;
    int accountID;

    public TradeDeleteDTO(@JsonProperty("ticketID") int ticketID, @JsonProperty("accountID") int accountID){
        this.ticketID = ticketID;
        this.accountID = accountID;
    }
}
