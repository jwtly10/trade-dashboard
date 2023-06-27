package com.jwtly10.tradedashboard.api;

import com.jwtly10.tradedashboard.service.MetricsService;
import com.jwtly10.tradedashboard.service.metaapi.MetaStatsService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/metastats")
@RequiredArgsConstructor
public class MetaStatsApiController {

    private final MetaStatsService metaStatsService;

    @Value("${meta.stats.api.account.id}")
    private String accountId;

    @GetMapping("/getOpenTrades")
    public ResponseEntity<?> getOpenTrades(){
        return ResponseEntity.ok(metaStatsService.getOpenTrades(accountId));
    }

    @GetMapping("/getHistoricTrades")
    public ResponseEntity<?> getHistoricTrades(){
        return ResponseEntity.ok(metaStatsService.getHistoricTrades(accountId));
    }

    @GetMapping("/getAllMetrics")
    public ResponseEntity<?> getAllMetrics() throws ParseException {
        MetricsService metricsService = new MetricsService(metaStatsService);
        return ResponseEntity.ok(metricsService.buildMetricsJSON(accountId).toString());
    }

    @GetMapping("/getMetrics")
    public ResponseEntity<?> getMetrics() throws ParseException {
        MetricsService metricsService = new MetricsService(metaStatsService);
        return ResponseEntity.ok(metricsService.getKeyMetrics(accountId).toString());
    }

}
