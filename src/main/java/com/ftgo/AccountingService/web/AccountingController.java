package com.ftgo.AccountingService.web;

import com.ftgo.AccountingService.AccountingService;
import com.ftgo.AccountingService.domain.account.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountingController {
    private final AccountingService accountingService;

    public AccountingController(@Autowired AccountingService accountingService) {
        this.accountingService = accountingService;
    }

    @GetMapping("/hcheck")
    @ResponseStatus(HttpStatus.OK)
    public HealthCheckResponse healthCheck() {
        return new HealthCheckResponse();
    }

    @PostMapping("/accounting/{consumerId}")
    public CreateAccountResponse create(@PathVariable long consumerId) {
        Account account = accountingService.createAccount(consumerId);
        return new CreateAccountResponse(account.getId());
    }
}
