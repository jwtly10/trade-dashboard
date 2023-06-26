package com.jwtly10.tradedashboard.service.metaapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class MetaStatsService {

    @Value("${meta.stats.api.base.url}")
    private String baseUrl;

    @Value("${meta.stats.api.user.token}")
    private String accountKey;

    @Autowired
    private RestTemplate restTemplate;

    public Object[] getOpenTrades(String accountId){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set("auth-token", accountKey);

            ResponseEntity<String> response = restTemplate.exchange(
                "%s/users/current/accounts/%s/open-trades".formatted(baseUrl, accountId),
                    HttpMethod.GET,
                    new HttpEntity<>(headers), String.class);

            log.info("Success: Retrieved open trades from meta-stats-api");

            return new String[]{response.getBody()};
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

            log.info("Success: Retrieved historic trades from meta-stats-api");

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
}