package com.ftgo.AccountingService.domain.account.entity;

import com.ftgo.AccountingService.domain.Money;
import lombok.Getter;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "caccounts")
@Access(AccessType.FIELD)
@Getter
public class Account {
    @Id
    @Column(name = "account_id")
    private Long id;

    @Column(name = "consumer_id")
    private Long consumerId;

    @Column(name = "order_id")
    private Long orderId;

    @Embedded
    private MoneyOnDB paymentAmount;

    public Account() {}

    public Account(Long consumerId, Long orderId, Money paymentAmount) {
        id = Math.abs(new Random().nextLong());
        this.consumerId = consumerId;
        this.orderId = orderId;
        this.paymentAmount = paymentAmount.transformEmbeddable();
    }
}
