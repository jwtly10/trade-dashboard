package com.jwtly10.tradedashboard.model;

import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
public class Account {
    int accountID;
    String accountKey;
    int accountSize;
    String accountType;
    Timestamp created;

}
