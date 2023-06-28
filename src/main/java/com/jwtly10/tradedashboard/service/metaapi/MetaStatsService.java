package com.jwtly10.tradedashboard.service.metaapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
public class MetaStatsService {

    @Value("${meta.stats.api.base.url}")
    private String baseUrl;

    @Value("${meta.stats.api.user.token}")
    private String accountKey;

    @Autowired
    private RestTemplate restTemplate;

    public String getOpenTrades(String accountId){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set("auth-token", accountKey);

            ResponseEntity<String> response = restTemplate.exchange(
                "%s/users/current/accounts/%s/open-trades".formatted(baseUrl, accountId),
                    HttpMethod.GET,
                    new HttpEntity<>(headers), String.class);

//            log.info("Success: Retrieved open trades from meta-stats-api");

            return response.getBody();

        }catch (Exception e){
            log.error("Failed to get open trades" + e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception while calling MetaStatsApi",
                    e
            );
        }
    }

    public String getHistoricTrades(String accountId){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set("auth-token", accountKey);

            ResponseEntity<String> response = restTemplate.exchange(
                    "%s/users/current/accounts/%s/historical-trades/2020-09-08 22:21:36.000/2023-06-25 22:21:36.000".formatted(baseUrl, accountId),
                    HttpMethod.GET,
                    new HttpEntity<>(headers), String.class);

//            log.info("Success: Retrieved historic trades from meta-stats-api");

            return response.getBody();

        }catch (Exception e){
            log.error("Failed to get all trades" + e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception while calling MetaStatsApi",
                    e
            );
        }
    }

    @Retryable(retryFor = HttpServerErrorException.class, maxAttempts = 2, backoff = @Backoff(delay = 100))
    public String getMetrics(String accountId){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set("auth-token", accountKey);

            ResponseEntity<String> response = restTemplate.exchange(
                    "%s/users/current/accounts/%s/metrics".formatted(baseUrl, accountId),
                    HttpMethod.GET,
                    new HttpEntity<>(headers), String.class);

            if (response.getStatusCode() == HttpStatus.OK){
                log.info("Success: Retrieved metrics from meta-stats-api");
            }else {
                log.error("Error getting metrics: " + response.getStatusCode() + " " + response.getBody());
                handleAsyncResponse(response);
            }

            return response.getBody();

        }catch (Exception e){
            log.error("Failed to get metrics" + e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception while calling MetaStatsApi",
                    e
            );
        }
    }

    public void handleAsyncResponse(ResponseEntity<String> response) {
        HttpStatusCode statusCode = response.getStatusCode();
        if (statusCode == HttpStatus.ACCEPTED){
            throw new HttpServerErrorException(statusCode, Objects.requireNonNull(response.getBody()));
        }
    }
}
