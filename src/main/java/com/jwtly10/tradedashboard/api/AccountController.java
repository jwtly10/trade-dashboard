package com.jwtly10.tradedashboard.api;


import com.jwtly10.tradedashboard.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173/")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/getAccounts")
    public ResponseEntity<?> getAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }
}
