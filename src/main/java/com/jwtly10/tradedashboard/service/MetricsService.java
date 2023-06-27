package com.jwtly10.tradedashboard.service;

import com.jwtly10.tradedashboard.service.metaapi.MetaStatsService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {

    private final MetaStatsService metaStatsService;

    @Autowired
    public MetricsService(MetaStatsService metaStatsService) {
        this.metaStatsService = metaStatsService;
    }

    public JSONObject buildMetricsJSON(String accountId) throws ParseException {
        JSONObject body = (JSONObject)
                new JSONParser().parse(metaStatsService.getMetrics(accountId));

        return (JSONObject) body.get("metrics");
    }
    
    public JSONObject getKeyMetrics(String accountId) throws ParseException {
        JSONObject metric = buildMetricsJSON(accountId);
        
        metric.remove("currencySummary");
        metric.remove("monthlyAnalytics");
        metric.remove("tradeDurationDiagram");
        metric.remove("closeTradesByWeekDay");
        metric.remove("openTradesByHour");
        metric.remove("tradeDuration");
        metric.remove("riskOfRuin");
        metric.remove("dailyGrowth");
        metric.remove("periods");

        return metric;
    }


}

