package com.ftgo.AccountingService.exception;

public class AccountDisabledException extends RuntimeException {
    public AccountDisabledException(Long consumerId) {
        super(consumerId.toString());
    }
}