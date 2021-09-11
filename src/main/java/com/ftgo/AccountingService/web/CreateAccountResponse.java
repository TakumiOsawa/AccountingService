package com.ftgo.AccountingService.web;

import lombok.Data;

@Data
public class CreateAccountResponse {
    private long accountId;

    public CreateAccountResponse(long accountId) {
        this.accountId = accountId;
    }
}
