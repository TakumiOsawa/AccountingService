package com.ftgo.AccountingService.domain.account.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MoneyOnDB {
    @Getter
    @Column(name = "payment_amount")
    private long value;

    public MoneyOnDB() {}

    public MoneyOnDB(long value) {
        this.value = value;
    }
}
