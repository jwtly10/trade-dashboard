package com.jwtly10.tradedashboard.api;

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
@CrossOrigin(origins = {"http://localhost:5173/", "https://trade-dashboard.netlify.app/"})
public class OverviewController {

    private final OverviewService overviewService;

    @GetMapping("/getOverview")
    public ResponseEntity<?> getOverview() {
        return ResponseEntity.ok(overviewService.buildOverview());
    }
}
