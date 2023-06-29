package com.jwtly10.tradedashboard.service;

import com.jwtly10.tradedashboard.model.Account;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverviewService {

    private final AccountService accountService;
    private final MetricsService metricsService;


    public OverviewService(AccountService accountService, MetricsService metricsService) {
        this.accountService = accountService;
        this.metricsService = metricsService;
    }

    public JSONObject buildOverview(){
        JSONObject overviewJSON = new JSONObject();
        List<Account> accountList = accountService.getAccounts();

        accountList.forEach(account ->{
            try {
                overviewJSON.put(
                        account.getAccountID(),
                        metricsService.getKeyMetrics(String.valueOf(account.getAccountKey())));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

        return overviewJSON;
    }
}
