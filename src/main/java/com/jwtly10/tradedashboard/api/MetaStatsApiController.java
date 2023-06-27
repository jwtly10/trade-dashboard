package com.jwtly10.tradedashboard.api;

import com.jwtly10.tradedashboard.service.MetricsService;
import com.jwtly10.tradedashboard.service.metaapi.MetaStatsService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/metastats")
@RequiredArgsConstructor
public class MetaStatsApiController {

    private final MetaStatsService metaStatsService;

    @GetMapping("/getOpenTrades/{accountKey}")
    public ResponseEntity<?> getOpenTrades(@PathVariable("accountKey") String accountKey){
        return ResponseEntity.ok(metaStatsService.getOpenTrades(accountKey));
    }

    @GetMapping("/getHistoricTrades/{accountKey}")
    public ResponseEntity<?> getHistoricTrades(@PathVariable("accountKey") String accountKey){
        return ResponseEntity.ok(metaStatsService.getHistoricTrades(accountKey));
    }

    @GetMapping("/getAllMetrics/{accountKey}")
    public ResponseEntity<?> getAllMetrics(@PathVariable("accountKey") String accountKey) throws ParseException {
        MetricsService metricsService = new MetricsService(metaStatsService);
        return ResponseEntity.ok(metricsService.buildMetricsJSON(accountKey).toString());
    }

@GetMapping("/getMetrics/{accountKey}")
    public ResponseEntity<?> getMetrics(@PathVariable("accountKey") String accountKey) throws ParseException {
        MetricsService metricsService = new MetricsService(metaStatsService);
        return ResponseEntity.ok(metricsService.getKeyMetrics(accountKey).toString());
    }

}
