package com.jwtly10.tradedashboard.service;

import com.jwtly10.tradedashboard.model.Account;
import org.json.simple.JSONArray;
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

    public JSONArray buildOverview() {
        JSONArray overviewJSONs = new JSONArray();
        List<Account> accountList = accountService.getAccounts();

        accountList.forEach(account -> {
            try {
                JSONObject node = new JSONObject();
                node = metricsService.getKeyMetrics(String.valueOf(account.getAccountKey()));
                node.put("accountID", account.getAccountID());
                node.put("accountKey", account.getAccountKey());
                node.put("accountSize", account.getAccountSize());
                node.put("accountType", account.getAccountType());
                node.put("accountCreated", account.getCreated());

                overviewJSONs.add(node);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        });

        return overviewJSONs;
    }
}
