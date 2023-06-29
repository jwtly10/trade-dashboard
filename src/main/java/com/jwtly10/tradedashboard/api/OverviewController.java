package com.jwtly10.tradedashboard.api;

import com.jwtly10.tradedashboard.service.AccountService;
import com.jwtly10.tradedashboard.service.MetricsService;
import com.jwtly10.tradedashboard.service.OverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/overview")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173/")
public class OverviewController {

    private final AccountService accountService;
    private final MetricsService metricsService;
    private final OverviewService overviewService;

    @GetMapping("/getOverview")
    public ResponseEntity<?> getOverview() {
        return ResponseEntity.ok(overviewService.buildOverview());
    }
}
